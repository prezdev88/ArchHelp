# Instalación Archlinux

## Guía oficial de instalación en ingles
https://wiki.archlinux.org/index.php/installation_guide

## Guía oficial de instalación en español
https://wiki.archlinux.org/index.php/Installation_guide_(Espa%C3%B1ol)

## Configuración del lenguaje
```bash
# Se carga el teclado español
loadkeys es
```	

```bash
# Descomentar es-ES.UTF-8
nano /etc/locale.gen 
```	

```bash
# Se genera el archivo locale del sistema
locale-gen
```	

```bash
# Se crea la variable del sistema LANG
export LANG=es_ES.UTF-8
```

## Configuración de WiFi

```bash
# Ver interfaces de red disponibles
ip link
```

```bash
# En mi caso la tarjeta de WiFi es wlo1
# Habilitando wlo1
ip link set wlo1 up
```

```bash
# Creando archivo de configuración de WPA Supplicant
nano /etc/wpa_supplicant/wpa_supplicant.conf
```

```bash
ctrl_interface=/run/wpa_supplicant 
update_config=1
```

```bash
# Ejecutando WPA Supplicant
wpa_supplicant -B -i wlo1 -c /etc/wpa_supplicant/wpa_supplicant.conf
```

```bash
# Entrando a la consola de WPA
wpa_cli
```

```bash
# Creando red nueva
> add_network 0

# Poniendo los datos de conexión
> set_network 0 ssid "MYSSID" 
> set_network 0 psk "passphrase"

# Habilitando red nueva
> enable_network 0

# Guardando la configuración
> save_config
> quit
```

```bash
# Petición de IP
dhcpcd wlo1

# Probando conexión
ping 8.8.8.8
```

## Configuración de red estática (Opcional)
```bash
# Para ver el nombre de la interfaz de red
ip link

ip link set ${interface} up

ip addr add ${ipAddress}/${subnetMask} broadcast ${broadcastAddress} dev ${interface}
```

```bash
# DNS
nano /etc/resolv.conf

# Añadir
nameserver 192.168.1.158
```

```bash
# Gateway
ip route adwd default via 10.52.1.1
```

```bash
# Proxy
export http_proxy=192.168.241.14:8080
```

```bash
# comprobar 
ping 8.8.8.8
```

## Actualizar el reloj del sistema
```bash
timedatectl status
```

## Particiones
La idea es crear esto:

|Device|Descripción|Código formato|Tamaño|
|-|-|-|-|
|sda1|EFI|EF00|250M|
|sda2|SWAP|8200|4G|
|sda3|SYSTEM|DEFAULT|Lo que sobra|

```bash
cgdisk /dev/sda
```

## Formato
```bash
# Formateando EFI
mkfs.fat -F32 /dev/sda1
```

```bash
# Formateando SWAP
mkswap /dev/sda2
swapon /dev/sda2
```

```bash
# Formateando SYSTEM
mkfs.ext4 /dev/sda3
```

```bash
# Comprobando lo realizado
lsblk -f
```

## Montar particiones

```bash
# Montar partición SYSTEM
mount /dev/sda3 /mnt
```

```bash
# Montar partición EFI
mkdir /mnt/boot
mount /dev/sda1 /mnt/boot 
```

## Instalación

```bash
dirmngr < /dev/null
```
```bash
pacman-key --populate archlinux
pacman-key --refresh-keys
```

## Si no funciona lo de arriba...

```bash
nano /etc/pacman.d/gnupg/gpg.conf
keyserver hkp://pool.sks-keyservers.net

pacman-key --populate archlinux
pacman-key --refresh-keys
```

```bash
pacman -Sy
pacman -S pacman
```

```bash
# Instalación
pacstrap -i /mnt base base-devel linux linux-firmware
```

```bash
# Verificar la instalación
ls /mnt
```

```bash
# Generar el archivo fstab
genfstab -U -p /mnt >> /mnt/etc/fstab

# Comprobar archivo
cat /mnt/etc/fstab
```

## Entrando al sistema

```bash
arch-chroot /mnt /bin/bash
```

## Instalación de apps necesarias

```bash
pacman -S 
nano
dhcpcd # deprecated (networkmanager)
networkmanager
bash-completion
wpa_supplicant
```

## Configuración del lenguaje

```bash
# Descomentar es-ES.UTF-8
nano /etc/locale.gen 
```	

```bash
# Se genera el archivo locale del sistema
locale-gen
```	

```bash
echo LANG=es_ES.UTF-8 > /etc/locale.conf
```

```bash
export LANG=es_ES.UTF-8
```

```bash
echo KEYMAP=es > /etc/vconsole.conf
```

## Configuración de hora y fecha

```bash
# Se establece la zona horaria
ln -s /usr/share/zoneinfo/America/Santiago /etc/localtime
```

```bash
# Se establece el reloj del hardware
hwclock --systohc --utc 
```

## Configuración de red

### DEPRECATED (ver Network manager)
```bash
# Para ver el nombre de la interfaz de red
ip link

# Habilitar dhcp service para la interfaz (Sólo si es Ethernet)
systemctl enable dhcpcd@${interface}
```

### Network manager
```bash
sudo systemctl enable NetworkManager
```

## Configuración variada

```bash
# Nombre de host
echo ${nombreEquipo} > /etc/hostname
```

```bash
# Password de usuario root
passwd
```

## Grub con Windows (Incluso con EFI)

```bash
# Instalación de apps necesarias
pacman -S grub efibootmgr os-prober
```

```bash
# Instalación de grub en /boot
sudo grub-install --target=x86_64-efi --efi-directory=/boot --bootloader-id=grub
```

```bash
# descomentar GRUB_DISABLE_OS_PROBER=false
sudo nano /etc/default/grub
```

```bash
# Acá debería salir algo de windows
sudo os-prober
```

```bash
# Crear la configuración para grub
sudo grub-mkconfig -o /boot/grub/grub.cfg
```

## Configuración EFI (Sólo si el PC soporta EFI)

```bash
# Instalando cgdisk
pacman -S gptfdisk
```

```bash
# Formatear de nuevo el boot como EF00
cgdisk /dev/sda
```

```bash
mount -t efivarfs efivarfs /sys/firmware/efi/efivars
```

```bash
bootctl install
```

```bash
# Ver el UUID de la partición SYSTEM y se debe copiar
blkid -s PARTUUID -o value /dev/sda3
```

```bash
nano /boot/loader/entries/arch.conf

# Dentro del archivo
title Arch Linux
linux /vmlinuz-linux
initrd /initramfs-linux.img
options root=PARTUUID=${PARTUUID} rw
```

```bash
# Salimos y reiniciamos sin el pendrive
exit
umount -R /mnt
reboot
```

## GRUB (Sólo si no es EFI)
```bash
# Instalando
pacman -S grub
```

```bash
# Configurando
grub-install --target=i386-pc --force /dev/sda
```

```bash
# Creando el archivo de configuración
grub-mkconfig -o /boot/grub/grub.cfg
```

```bash
# Salimos y reiniciamos sin el pendrive
exit
umount -R /mnt
reboot
```

## Configuración de WiFi
Acá se requiere nuevamente la configuración de WiFi o Ethernet.

## Creación de usuario

```bash
# Ingresar con las credenciales root al sistema
# Creación de usuario
useradd -m -g users -s /bin/bash ${nombreDeUsuario}
```
 
```bash
# Estableciendo la password del usuario
passwd ${nombreDeUsuario}
```

```bash
# Configuración de pacman (MultiLib)
# Descomentar la linea [multilib] e include
nano /etc/pacman.conf

# Actualizamos de nuevo
pacman -Syu
```

```bash
# Instalar sudo
pacman -S sudo
```

```bash
# Añadir a ${nombreDeUsuario} a los usuarios sudo
EDITOR=nano visudo
 
# Añadir abajo de root ALL=(ALL) ALL
${nombreDeUsuario} ALL=(ALL) ALL
```

```bash
# Ahora podemos cerrar sesión y entrar con la nueva cuenta
```

## Instalación de xorg

|Driver|Comando|
|-|-|
|Genérico|xf86-video-vesa|   
|Ati|xf86-video-ati|   
|Intel|xf86-video-intel|   
|NVIDIA|xf86-video-nouveau|  

```bash
# Instalación de paquetes necesarios (Drivers genéricos)
sudo pacman -S 
    xorg-server 
    xorg-xinit
    xorg-apps 
    mesa 
    mesa-demos 
    xf86-video-vesa 
    xorg-twm 
    xorg-xclock 
    xterm
```

```bash
# Iniciando servidor X
startx
```
Si en este momento de abre un terminal gráfico (xterm) y se ve el cursor del mouse, esta todo OK.

```bash
# Para salir del servidor X
pkill x
```

## Teclado en español al iniciar servidor X
```bash
nano .xinitrc
setxkbmap -layout es
```

# Instalación de software opcional

## GIT

```bash
sudo pacman -S git
```

## CA certificates
```bash
sudo pacman -S ca-certificates-utils
```

## yay

```bash
# Como usuario normal
git clone https://aur.archlinux.org/yay.git
cd yay
makepkg -si
```

## Fuentes de Google

```bash
yay -S ttf-google-fonts-git
```
# Desktop environments

## Openbox

```bash
sudo pacman -S openbox
```

### Default configuration
```bash
mkdir -p ~/.config/openbox
cp /etc/xdg/openbox/{rc.xml,menu.xml,autostart,environment} ~/.config/openbox
```

### Fondo de pantalla
```bash
sudo pacman -S feh

nano .config/openbox/autostart

feh --bg-scale ${image-path} &
```

### obconf
```bash
# Configuración de openbox
sudo pacman -S obconf
```

### Menumaker
```bash
# Generar menu.xml automáticamente según programas instalados
sudo pacman -S menumaker
mmaker -v -f OpenBox3
openbox --reconfigure
```

### dmenu (krunner like)
```bash
sudo pacman -S dmenu

nano .config/openbox/rc.xml

<keybind key="A-space">
  <action name="Execute">
    <command>dmenu_run</command>
  </action>
</keybind>

openbox --reconfigure
```

### thunar
```bash
sudo pacman -S thunar
```

### tint2
```bash
# Panel inferior básico
sudo pacman -S tint2
```

### red
```bash
sudo pacman -S network-manager-applet

# añadir esto a .xinitrc
nm-applet &
```

### Look and feel (themes)
```bash
# Look and feel de openbox
sudo pacman -S lxappearance
sudo pacman -S arc-gtk-theme
```

```bash
# Preferencias de energía
sudo pacman -S xfce4-power-manager
```

```bash
# Ícono de volumen en bandeja del sistema
sudo pacman -S volumeicon
```

```bash
# Extractor de zip gráfico
sudo pacman -S file-roller
```

```bash
# Ver particiones ntfs
sudo pacman -S ntfs-3g
```

### Audio (t14)
```bash
sudo pacman -S sof-firmware
sudo pacman -S alsa-utils alsa-plugins pulseaudio pulseaudio-alsa pavucontrol
pulseaudio --start
reboot
pavucontrol

# icono de volumen
sudo pacman -S pasystray
```

### Colorpicker
```bash
sudo pacman -S colorpicker
```

```bash
# .xinitrc
tint2 &
nm-applet &
pasystray &
conky &
setxkbmap -layout es
exec openbox-session
```

## Qtile

```bash
sudo pacman -S qtile
```

```bash
# Creando la carpeta para la configuración de qtile
$ mkdir -p ~/.config/qtile/ 

# Copiando la configuración de qtile por defecto
$ cp /usr/share/doc/qtile_dir/default_config.py ~/.config/qtile/config.py

# Ejecución de qtile
# Añadir esta linea a .xinitrc
exec qtile

# Cambiar colores de terminal xterm
nano .Xdefaults 

XTerm*Background: black 
XTerm*Foreground: white
```

### Rofi
```bash
yay -S rofi

# Agregar esto a config.py (si es que tienes qtile)
# Esto permite que se abra rofi con ctrl + win + space
Key([mod, "control"], "space", lazy.spawn("rofi -show run"), desc="Run rofi"),
```

#### Rofi dark mode
https://github.com/bardisty/gruvbox-rofi

## KDE
```bash
sudo pacman -S plasma kde-applications-meta
```
### .xinitrc
```bash
nano .xinitrc
exec startplasma-x11
```

### Display Manager
```bash
systemctl enable sddm.service
```

### Error en audio
```bash
sudo pacman -S sof-firmware
```

### Autologin
```bash
nano /etc/sddm.conf.d/autologin.conf
```
```bash
[Theme]
Current=breeze

[Autologin]
User=prez
Session=plasma.desktop
```

### Widgets
```bash
netspeed
thermal
resources
fokus
launchpad Plasma
```

## Mate
```bash
sudo pacman -S mate mate-extra
```
### .xinitrc
```bash
nano .xinitrc
exec mate-session
```
### Wifi Manager
```bash
sudo pacman -S networkmanager network-manager-applet
systemctl enable NetworkManager.service
```

## Gnome
```bash
sudo pacman -S gnome gnome-extra gnome-power-manager gnome-tweak-tool gnome-packagekit gnome-settings-daemon-updates polkit-gnome gnome-keyring
```
### Display Manager
```shell
systemctl enable gdm.service
```

## Network Manager
```bash
systemctl enable NetworkManager.service
```

### .xinitrc
```bash
nano .xinitrc
exec gnome-session
```


# Instalación de aplicaciones varias
```shell
yay -S yakuake neofetch jdk8-openjdk htop google-chrome vscodium-bin postman-bin intellij-idea-community-edition intellij-idea-ultimate-edition intellij-idea-ultimate-edition-jre dbeaver git maven pulseaudio-equalizer-ladspa swh-plugins gstreamer pulseaudio pulseaudio-alsa bluez bluez-utils gst-plugins-ugly ntfs-3g exfat-utils fuse-exfat wine pulseaudio-bluetooth obs-studio brave-bin acetoneiso2 gparted flameshot openboard chromium notion-app gitahead-bin discord slack-desktop cool-retro-term forticlient-vpn 
```

# fish
## Install
```bash
yay -S fish
curl -L https://get.oh-my.fish | fish
```

## themes
```bash
omf theme
omf install agnoster
```

# Iniciar y habilitar servicio bluetooth
```bash
systemctl start bluetooth.service
systemctl enable bluetooth.service
```

# gpg: recepción del servidor de claves fallida: Error general
```bash
# En /home/{user}
nano .gnupg/gpg.conf
keyserver pool.sks-keyservers.net
```

# gpg: recepción del servidor de claves fallida: Sin nombre
```bash
nano /etc/pacman.d/gnupg/gpg.conf
keyserver hkp://pgp.mit.edu:11371
pacman -Sc
pacman-key --populate archlinux
pacman-key --refresh-keys
```

# Wine audio error
```bash
yay -S lib32-mpg123 lib32-libpulse lib32-alsa-plugins
```
