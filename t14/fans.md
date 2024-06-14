# Manual fan control

## Cargar el módulo thinkpad_acpi con opciones adecuadas
```bash
sudo nano /etc/modprobe.d/thinkpad_acpi.conf
```

Agregar la siguiente línea:

```bash
options thinkpad_acpi fan_control=1
```

Luego, recarga el módulo
```bash
sudo modprobe -r thinkpad_acpi
sudo modprobe thinkpad_acpi fan_control=1
```

## Control

### Ver el estado actual de ventilador
```bash
cat /proc/acpi/ibm/fan
```

### Controla la velocidad del ventilador (level 1 - 7)
```bash
echo level 3 | sudo tee /proc/acpi/ibm/fan
```