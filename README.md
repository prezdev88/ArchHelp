# Instalación Archlinux

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

## Configuración de red estática
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
wget www.google.cl 
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

```bash
# Instalación
pacstrap -i /mnt base base-devel
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

```bash
# Para ver el nombre de la interfaz de red
ip link

# Habilitar dhcp service para la interfaz
systemctl enable dhcpcd@${interface}
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

```bash
# Instalando bash-completion
pacman -S bash-completion
```

## Configuración EFI

```bash
# Instalando cgdisk
pacman -S cgdisk
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
# Ahora podemos desloguearnos y entrar con la nueva cuenta
```

## Instalación de xorg

|Driver|Comando|
|-|-|
|Genérico|xf86-video-vesa|   
|Ati|xf86-video-ati|   
|Intel|xf86-video-intel|   
|NVIDIA|xf86-video-nouveau|  

```bash
pacman -S 
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
startx
```

```bash
# Para salir de xorg
pkill x
```

## Instalación de aplicaciones

```bash
# yay
pacman -S git
git clone https://aur.archlinux.org/yay.git
cd yay
makepkg -si
```







































