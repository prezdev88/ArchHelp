# KDE
```bash
sudo pacman -S plasma kde-applications-meta
```
## .xinitrc
```bash
nano .xinitrc
exec startplasma-x11
```

## Display Manager
```bash
systemctl enable sddm.service
```

## Error en audio
```bash
sudo pacman -S sof-firmware
```

## Autologin
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

## Widgets
```bash
netspeed
thermal
resources
fokus
launchpad Plasma
```