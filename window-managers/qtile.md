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