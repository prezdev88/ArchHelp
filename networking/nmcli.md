# NmCLI

## Installation
```bash
sudo pacman -S networkmanager
sudo systemctl enable NetworkManager
sudo systemctl start NetworkManager
```

## Devices list
```bash
nmcli device wifi list
```

## Connect
```bash
nmcli device wifi connect "SSID" password "PASSWORD"
```

## Disconnect
```bash
nmcli connection down id "SSID"
```
