# Qtile

```bash
sudo pacman -S qtile
```

```bash
# Creando la carpeta para la configuración de qtile
$ mkdir -p ~/.config/qtile/ 

# Copiando la configuración de qtile por defecto
$ cp /usr/share/doc/qtile/default_config.py ~/.config/qtile/config.py

# Ejecución de qtile
# Añadir esta linea a .xinitrc
exec qtile start

# Cambiar colores de terminal xterm
nano .Xdefaults 

XTerm*Background: black 
XTerm*Foreground: white
```

## config
```bash
Key([mod], "r", lazy.spawn("dmenu_run"))
```

## Battery (donde están todos los demás widget)
```python
widget.Battery(
    format='{char} {percent:2.0%} ({hour:d}:{min:02d})',
    charge_char='⚡',
    discharge_char='⚡',
    full_char='⚡',
    notify_below=20,  # Opcional: mostrar notificación cuando la batería esté por debajo del 20%
    update_interval=60  # Opcional: actualizar cada 60 segundos
)
```