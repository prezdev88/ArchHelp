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