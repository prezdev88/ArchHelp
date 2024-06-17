# Bluetooth

## Apps
```bash
sudo pacman -S bluez bluez-utils pulseaudio-bluetooth blueman
```

## Iniciar servicio de bluetooth
```bash
sudo systemctl start bluetooth
sudo systemctl enable bluetooth
```

## Bluetooth Gui
```bash
# Tray icon (Para encender el dispositivo)
blueman-applet

# Para conectar a través de GUI
blueman-manager
```

## bluetoothctl (cli)
```bash
bluetoothctl
```

```bash
# Enciende el Bluetooth.
power on

# Habilita el agente de emparejamiento.
agent on

# Establece el agente predeterminado.
default-agent

# Escanea dispositivos Bluetooth cercanos.
scan on

# Emparejamiento
pair ${mac-address}

# Confiar en el dispositivo
trust ${mac-address}

# Conectar con dispositivo
connect ${mac-address}
```

## Añadir a inicio del sistema (i3 config)
```bash
exec --no-startup-id blueman-applet
```
