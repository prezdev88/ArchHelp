# Gnome
```bash
sudo pacman -S gnome gnome-extra gnome-power-manager gnome-tweak-tool gnome-packagekit gnome-settings-daemon-updates polkit-gnome gnome-keyring
```
## Display Manager
```shell
systemctl enable gdm.service
```

## Network Manager
```bash
systemctl enable NetworkManager.service
```

## .xinitrc
```bash
nano .xinitrc
exec gnome-session
```