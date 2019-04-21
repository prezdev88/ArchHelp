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
```bash
# La idea es crear esto

# sda1 -- EFI	 -- EF00 	-- 250M	
# sda2 -- SWAP	 -- 8200 	-- 4G
# sda3 -- SYSTEM -- DEFAULT -- Lo que sobra	
```

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


