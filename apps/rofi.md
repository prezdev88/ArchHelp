# Rofi

## Install
```bash
sudo pacman -S rofi
```

## Search projects and open with idea
```bash
sudo nano /bin/search-projects
```

```bash
#!/bin/sh

# Define the paths
path1="$path"
path2="$path"
path3="$path"

# Execute find for each path and store the output in separate variables
projects_path1=$(find "$path1" -maxdepth 1 -mindepth 1 -type d -printf '%p\n')
projects_path2=$(find "$path2" -maxdepth 1 -mindepth 1 -type d -printf '%p\n')
projects_path3=$(find "$path3" -maxdepth 1 -mindepth 1 -type d -printf '%p\n')

# Concatenate the outputs of the three finds
projects=$(printf "%s\n%s\n%s\n" "$projects_path1" "$projects_path2" "$projects_path3")

# Pass the concatenated projects to rofi
echo "$projects" | rofi -dmenu -p "Open project" -lines 10 -width 1000 | xargs -I {} idea-ce {}
```

```bash
chmod +x /bin/search-projects
```

## Open with 'window + i'
```bash
nano .config/openbox/rc.xml
```

```bash
<keybind key="W-i">
    <action name="Execute">
    <command>search-projects</command>
    </action>
</keybind>
```

## Theme
```bash
rofi-theme-selector
```
