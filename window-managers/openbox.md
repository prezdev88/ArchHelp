# Openbox

## Install
```bash
sudo pacman -S openbox
```

## Default configuration
```bash
mkdir -p ~/.config/openbox
cp /etc/xdg/openbox/{rc.xml,menu.xml,autostart,environment} ~/.config/openbox
```

## Background image
```bash
sudo pacman -S feh
nano .config/openbox/autostart

feh --bg-scale ${image-path} &

# Otra forma
feh --image-bg "#1D2021" --bg-center /home/prezdev/images/wallpaper.png
```

## obconf
```bash
sudo pacman -S obconf
```

## menumaker

To generate a menu.xml automatically based on installed programs,

```bash
sudo pacman -S menumaker
mmaker -v -f OpenBox3
openbox --reconfigure
```

## dmenu (krunner like)
```bash
sudo pacman -S dmenu
nano .config/openbox/rc.xml
```

```xml
<keybind key="A-space">
  <action name="Execute">
    <command>dmenu_run</command>
  </action>
</keybind>
```

```bash
openbox --reconfigure
```

## thunar
```bash
sudo pacman -S thunar
```

## tint2

Basic panel

```bash
sudo pacman -S tint2
```

## lxqt-config (control panel)
```bash
sudo pacman -S lxqt-config
```

## red
```bash
sudo pacman -S network-manager-applet
```

### Añadir esto a .xinitrc
```bash
nm-applet &
```

## Look and feel (themes)
```bash
sudo pacman -S lxappearance arc-gtk-theme
```

## Power preferences
```bash
sudo pacman -S xfce4-power-manager
```

## Volume icon on panel (Choose only one)
```bash
sudo pacman -S volumeicon
sudo pacman -S pasystray
```

## Graphical ZIP extractor
```bash
sudo pacman -S file-roller
```

## View NTFS partitions
```bash
sudo pacman -S ntfs-3g
```

## Audio (only on t14 notebook)
```bash
sudo pacman -S sof-firmware
sudo pacman -S alsa-utils alsa-plugins pulseaudio pulseaudio-alsa pavucontrol
pulseaudio --start
reboot
pavucontrol
```

## Colorpicker
```bash
sudo pacman -S colorpicker
```

## Clipboard
```bash
sudo pacman -S clipit
```

## Flameshot
```bash
sudo pacman -S flameshot
```

### Keybinding flameshot in openbox
```bash
nano ~/.config/openbox/rc.xml
```

```xml
<keybind key="Print">
  <action name="Execute">
    <command>flameshot gui</command>
  </action>
</keybind>
```

```bash
openbox --reconfigure
```

## .xinitrc
```bash
tint2 &
nm-applet &
pasystray &
conky &
clipit &
flameshot &
setxkbmap -layout es
exec openbox-session
```

## Mounting
### Manual mounting with udisks2
```bash
sudo pacman -S udisks2

# Listado de dispositivos
lsblk

udisksctl mount -b /dev/sdc1
udisksctl unmount -b /dev/sdc1
```

### Mount with GUI (udisks2 required)
```bash
sudo pacman -S udiskie

# .xinitrc
udiskie --tray &
```

## Dunst notification manager
```bash 
sudo pacman -S dunst

# Configuration file
mkdir -p ~/.config/dunst && echo -e "[global]\nfont = Sans 15" > ~/.config/dunst/dunstrc 

# .xinitrc
dunst &

# notification test
notify-send "Prueba de Notificación" "Esta es una prueba de notificación."
```

## picom
```bash
sudo pacman -S picom
sudo mv /etc/xdg/picom.conf /etc/xdg/picom.conf.bak
sudo nano /etc/xdg/picom.conf
```

```bash
# Shadows
shadow = true;
shadow-radius = 8;
shadow-opacity = 0.6;
shadow-offset-x = -3;
shadow-offset-y = -3;
shadow-exclude = [
  "class_g ?= 'i3-frame'"
];
# Fading
fading = true;
fade-in-step = 0.03;
fade-out-step = 0.03;
fade-delta = 4;
# Transparency / Opacity
inactive-opacity = 1;
frame-opacity = 1.0;
inactive-opacity-override = false;
detect-client-opacity = true;
focus-exclude = [ "class_g = 'Cairo-clock'" ];
opacity-rule = [
  "90:class_g = 'URxvt'",
  "97:class_g = 'Anki'",
  "70:class_g = 'i3bar'"
];
# General settings
backend = "glx";
vsync = true;
mark-wmwin-focused = true;
mark-ovredir-focused = true;
detect-rounded-corners = true;
detect-client-opacity = true;
# refresh-rate = 75;
use-ewmh-active-win = true;
detect-transient = true;
detect-client-leader = true;
use-damage = true;
log-level = "warn";
wintypes:
{
  tooltip = { fade = true; shadow = true; opacity = 1; focus = true; full-shadow = false; };
  dock = { shadow = false; opacity: 0.8; }
  dnd = { shadow = false; }
  popup_menu = { opacity = 0.8; }
  dropdown_menu = { opacity = 1; }
};
unredir-if-possible = false;

blur-background = true;
blur-method = "dual_kawase";
blur-strength = 1;
```

### picom in .xinitrc
```bash
picom &
```

## Themes
```bash
yay -S openbox-themes
```

## Icons
```bash
yay -S emerald-icon-theme el-general-icon-theme-git humanity-icon-theme la-capitaine-icon-theme nitrux-icon-theme win11-icon-theme-git windows-xp-icon-theme windows10-icon-theme arc-icon-theme adwaita-icon-theme
```

## Calendar on tint2
```bash
yay -S orage gsimplecal
nano .config/tint2/tint2rc
clock_lclick_command = gsimplecal
```

## Brightness adjustment
```bash
# connected screens
xrandr -q

# Maximum brightness
cat /sys/class/backlight/*/max_brightness

# Set brightness
xrandr --output eDP1 --set BACKLIGHT 2000
```

## Window position Key binding (rc.xml)
```bash
nano .config/openbox/rc.xml
```

```xml
<!-- Move the Current Window to the Left Half of the Screen -->
<keybind key="C-W-Left">
  <action name="MoveResizeTo">
    <x>0</x>
    <y>0</y>
    <width>50%</width>
    <height>100%</height>
  </action>
</keybind>

<!-- Move the Current Window to the Right Half of the Screen -->
<keybind key="C-W-Right">
  <action name="MoveResizeTo">
    <x>-1</x>
    <y>0</y>
    <width>50%</width>
    <height>100%</height>
  </action>
</keybind>

<!-- Move the Current Window to the Top of the Screen -->
<keybind key="C-W-Up">
  <action name="Unmaximize"/>
  <action name="MoveResizeTo">
    <x>0</x>
    <y>0</y>
    <width>100%</width>
    <height>50%</height>
  </action>
</keybind>

<!-- Move the Current Window to the Bottom of the Screen -->
<keybind key="C-W-Down">
  <action name="Unmaximize"/>
  <action name="MoveResizeTo">
    <x>-1</x>
    <y>-1</y>
    <width>100%</width>
    <height>50%</height>
  </action>
</keybind>

<!-- Maximize Current Window -->
<keybind key="C-W-Return">
  <action name="Maximize"/>
</keybind>
```

# t14
## How to mount a microSD card?"
```bash
sudo mount /dev/mmcblk0p1 /mnt
```

# Switch audio output devices (pipewire-pulse)
Todo lo demás sólo funciona con pipewire-pulse, y no con pulseaudio

## Verify pipewire-pulse
```bash
systemctl --user status pipewire-pulse
```

## List of audio output devices
```bash
pactl list sinks | grep "Name:"
```

## Set a audio output device
```bash
pactl set-default-sink <device-name>
```

## aswitch (in /bin)
```bash
sudo nano /bin/aswitch
```

```bash
#!/bin/bash

current_sink=$(pactl info | grep 'Default Sink' | awk '{print $3}')

if [ "$current_sink" == "alsa_output.usb-Samson_Technologies_Samson_G-Track_Pro_4DF238142D143B00-00.analog-stereo" ]; then
    new_sink="alsa_output.pci-0000_00_1f.3.analog-stereo"
else
    new_sink="alsa_output.usb-Samson_Technologies_Samson_G-Track_Pro_4DF238142D143B00-00.analog-stereo"
fi

pactl set-default-sink "$new_sink"

echo "Audio cambiado a $new_sink"
```

```bash
sudo chmod +x /bin/aswitch
```

## aswitch as shortcut (win + s)

```bash
nano .config/openbox/rc.xml
```

```bash
<keybind key="W-s">
    <action name="Execute">
        <command>aswitch</command>
    </action>
</keybind>
```