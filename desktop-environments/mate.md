# Mate
```bash
sudo pacman -S mate mate-extra
```
## .xinitrc
```bash
nano .xinitrc
exec mate-session
```
## Wifi Manager
```bash
sudo pacman -S networkmanager network-manager-applet
systemctl enable NetworkManager.service
```