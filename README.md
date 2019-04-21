# Instalación Archlinux

```bash
# Se carga el teclado español
loadkeys es
```	

```bash
# Descomentar es-ES.UTF-8
nano /etc/locale.gen 
```	

```bash
# Se genera el archivo locale del sistema
locale-gen
```	

```bash
# Se crea la variable del sistema LANG
export LANG=es_ES.UTF-8
```
