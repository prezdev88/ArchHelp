# Instalación de aplicaciones varias
```shell
yay -S yakuake neofetch jdk8-openjdk htop google-chrome vscodium-bin postman-bin intellij-idea-community-edition intellij-idea-ultimate-edition intellij-idea-ultimate-edition-jre dbeaver git maven pulseaudio-equalizer-ladspa swh-plugins gstreamer pulseaudio pulseaudio-alsa bluez bluez-utils gst-plugins-ugly ntfs-3g exfat-utils fuse-exfat wine pulseaudio-bluetooth obs-studio brave-bin acetoneiso2 gparted flameshot openboard chromium notion-app gitahead-bin discord slack-desktop cool-retro-term forticlient-vpn 
```

## Codecs
```bash
sudo pacman -S ffmpeg gstreamer gst-plugins-good gst-plugins-bad gst-plugins-ugly
```

## Rofi
```bash
yay -S rofi

# Agregar esto a config.py (si es que tienes qtile)
# Esto permite que se abra rofi con ctrl + win + space
Key([mod, "control"], "space", lazy.spawn("rofi -show run"), desc="Run rofi"),
```

### Rofi dark mode
https://github.com/bardisty/gruvbox-rofi

## Iniciar y habilitar servicio bluetooth
```bash
systemctl start bluetooth.service
systemctl enable bluetooth.service
```

## xscreensaver
```bash
yay -S xscreensaver
```

### in .xinitrc
```bash
xscreensaver &
```

### Execute
```bash
xscreensaver-command -lock
```
