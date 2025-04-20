
# PROJECT-RPI

https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html

Setup: https://www.youtube.com/watch?v=VH4y-wBOCPk

SSH Remote: https://www.youtube.com/watch?v=ltckiBV9FXg

sudo apt install build-essential chrpath cpio debianutils diffstat file gawk gcc git iputils-ping libacl1 liblz4-tool locales python3 python3-git python3-jinja2 python3-pexpect python3-pip python3-subunit socat texinfo unzip wget xz-utils zstd

## Download Yocto Build Poky
1. git clone git://git.yoctoproject.org/poky
2. cd poky; git checkout origin/scarthgap

## Download Layer RaspberryPi
3. git clone https://git.yoctoproject.org/meta-raspberrypi
4. cd meta-raspberrypi; git checkout origin/scarthgap

## Compile Source Code
5. source oe-init-build-env
6. vi conf/bblayers.conf
   Add Layer (/home/ranganadh/GITHUB/PROJECT27/YoctoLinuxPI3/poky/meta-raspberrypi)
7. vi conf/local.conf
   Add Machine or Drivers
   MACHINE ??= "raspberrypi3"
   LICENSE_FLAGS_ACCEPTED = "synaptics-killswitch"
   GPU_MEM = "16"
9. sudo apparmor_parser -R /etc/apparmor.d/unprivileged_userns
10. bitbake -c clean openssl; bitbake openssl; bitbake -c clean sqlite3; bitbake sqlite3;
11. sudo apparmor_parser -R /etc/apparmor.d/unprivileged_userns;bitbake rpi-test-image -c cleanall;
12. bitbake rpi-test-image

## Add Recipe for WIFI configuration
1. Add IMAGE_INSTALL_append = " wpa-supplicant" in local.conf
2. Create wpa_supplicant.conf in recipes-connectivity/wifi-config/files
         ctrl_interface=DIR=/var/run/wpa_supplicant GROUP=netdev
         update_config=1
         country=<Your_Country_Code>  # e.g., US, IN, etc.
         network={
             ssid="<Your_WiFi_SSID>"
             psk="<Your_WiFi_Password>"
         }
3. Create Recipe wifi-config in recipes-connectivity/wifi-config and create wifi-config.bb then add below contents
         DESCRIPTION = "WiFi configuration"
         LICENSE = "CLOSED"
         
         SRC_URI = "file://wpa_supplicant.conf"
         FILES_${PN} += "/etc/wpa_supplicant.conf"
         
         do_install() {
             install -d ${D}/etc
             install -m 0644 ${WORKDIR}/wpa_supplicant.conf ${D}/etc/wpa_supplicant.conf
}
4. Create .bbapend file in recipes-connectivity/wpa_supplicant as wpa-supplicant.bbappend then add below contents
         SYSTEMD_AUTO_ENABLE:wpa-supplicant = "enable"
         SYSTEMD_SERVICE:wpa-supplicant = "wpa_supplicant.service"

## Add Recipe for SSH configuration
5. IMAGE_INSTALL:append = " openssh"
6. mkdir -p meta-yourlayer/recipes-connectivity/openssh/
7. touch meta-yourlayer/recipes-connectivity/openssh/openssh_%.bbappend then add below contents
8. SYSTEMD_AUTO_ENABLE:append = " sshd"
   SYSTEMD_SERVICE:append = " sshd.service"
9. INHERIT += "extrausers"
   EXTRA_USERS_PARAMS = "usermod -P <password> root;" in local.conf

## Add Recipe for LCD configuration
10. IMAGE_INSTALL:append = " lcd-utils"

11. bitbake rpi-test-image

12. cd tmp/deploy/images/raspberrypi3; ls -l *.wic*
13. bzip2 -d -f rpi-test-image-raspberrypi3.rootfs.wic.bz2
14. ls -l *.wic*

## Flash RaspberryPi3 Image
13. ls -l /dev/sda
14. gparted &
15. sudo dd if=rpi-test-image-raspberrypi3.rootfs.wic of=/dev/mmcblk0 status=progress bs=4M
16. cd /media/ranganadh/boot
17. vi config.txt ->
    enable uart=1
    dtparam=spi=on
    dtoverlay=spi0-1cs  # For SPI0 with 1 chip select
    #dtoverlay=spi0-1cs,cs0_pin=8,cs0_spidev=disabled
    sudo reboot

## Remote Access Raspberry Pi
1. ssh ranganadh@192.168.1.5 or ranganadh@raspberrypi.local

## Multiple Screens on Raspberry Pi via SSH
1. sudo apt-get install screen
2. screen -S session1
3. screen -r session1
4. screen -S session2
5. screen -ls
6. screen -XS my_session kill

## Copy Files between Raspberry Pi and Main Machine
1. scp myfile.txt ranganadh@192.168.1.5:project/
2. scp ranganadh@192.168.1.5:myfile.txt
