# Instalación de aplicaciones varias
```shell
yay -S yakuake neofetch jdk8-openjdk htop google-chrome vscodium-bin postman-bin intellij-idea-community-edition intellij-idea-ultimate-edition intellij-idea-ultimate-edition-jre dbeaver git maven pulseaudio-equalizer-ladspa swh-plugins gstreamer pulseaudio pulseaudio-alsa bluez bluez-utils gst-plugins-ugly ntfs-3g exfat-utils fuse-exfat wine pulseaudio-bluetooth obs-studio brave-bin acetoneiso2 gparted flameshot openboard chromium notion-app gitahead-bin discord slack-desktop cool-retro-term forticlient-vpn 
```

## Codecs
```bash
sudo pacman -S ffmpeg gstreamer gst-plugins-good gst-plugins-bad gst-plugins-ugly
```

## Rofi

### Install
```bash
sudo pacman -S rofi

# Agregar esto a config.py (si es que tienes qtile)
# Esto permite que se abra rofi con ctrl + win + space
Key([mod, "control"], "space", lazy.spawn("rofi -show run"), desc="Run rofi"),
```

### Theme
```bash
rofi-theme-selector
```

### Rofi dark mode
https://github.com/bardisty/gruvbox-rofi


## xscreensaver
```bash
yay -S xscreensaver
```

### in .xinitrc
```bash
xscreensaver &
```

### Create lock in bin
```bash
sudo bash -c 'echo "xscreensaver-command -lock" > /bin/lock && chmod +x /bin/lock'
```

## i3 bumblebee-status
```bash
git clone https://github.com/tobi-wan-kenobi/bumblebee-status ~/.config/bumblebee-status
```

```bash
yay -S ttf-hack-nerd python-requests nerd-fonts
```

### i3 config
```bash
bar {
	i3bar_command i3bar --transparency
	# status_command i3status

	# https://github.com/tobi-wan-kenobi/bumblebee-status
	# https://bumblebee-status.readthedocs.io/en/main/modules.html
	status_command ~/.config/bumblebee-status/bumblebee-status \
	-t greyish-powerline \
	-m ping weather -p weather.location=talca \
	-m kernel disk \
	-m sensors2 -p sensors2.chip='sensors -u' sensors2.showname=true \
	-m memory aur-update arch-update date -p date.format='%a, %d %b %Y' \
	-m time -p time.format='%H:%M'
	# position top
	output DP-4
	tray_output primary
	colors {
		background #00000000
		statusline #ffffff
		separator #666666

		focused_workspace #4c7899 #285577 #ffffff
		active_workspace #333333 #5f676a #ffffff
		inactive_workspace #333333 #222222 #888888
		urgent_workspace #2f343a #900000 #ffffff
		binding_mode #2f343a #900000 #ffffff
	}
}
```

## Agregar neofetch en bash y fish
```bash
echo "neofetch" >> ~/.bashrc
echo "neofetch" >> ~/.config/fish/config.fish
```

### Temas neofetch
https://github.com/Chick2D/neofetch-themes?tab=readme-ov-file

### ¿Donde poner esos temas?
```bash
nano ~/.config/neofetch/config.conf
```

## Bluetooth
```bash
sudo pacman -S bluez bluez-utils pulseaudio-bluetooth
sudo systemctl start bluetooth
sudo systemctl enable bluetooth
bluetoothctl

power on: Enciende el Bluetooth.
agent on: Habilita el agente de emparejamiento.
default-agent: Establece el agente predeterminado.
scan on: Escanea dispositivos Bluetooth cercanos.

Una vez que tu dispositivo sea detectado, utiliza el 
comando pair MAC_address (reemplaza MAC_address por 
la dirección MAC de tu dispositivo) para emparejarlo.
Después de emparejarlo, usa trust MAC_address para 
confiar en el dispositivo.
Finalmente, usa connect MAC_address para conectar el dispositivo.

connect 38:18:4C:4C:0D:EF
```

## Ranger (terminal file explorer)
```bash
sudo pacman -S ranger
```

### Icons in ranger
```bash
git clone https://github.com/alexanderjeurissen/ranger_devicons ~/.config/ranger/plugins ranger_devicons
echo "default_linemode devicons" >> $HOME/.config/ranger/rc.conf
```

# backlight (brillo de pantalla de laptops)
```bash
sudo pacman -S brightnessctl
brightnessctl <num>
```

## backlight i3 config
```bash
bindsym XF86MonBrightnessDown exec brightnessctl s 10%-
bindsym XF86MonBrightnessUp exec brightnessctl s +10%
```
