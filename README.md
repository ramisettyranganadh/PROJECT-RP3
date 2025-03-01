
# PROJECT27-REPOSITORY

https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html

Setup: https://www.youtube.com/watch?v=VH4y-wBOCPk

SSH Remote: https://www.youtube.com/watch?v=ltckiBV9FXg

# DOWNLOAD YOCTO BUILD POKY
1. git clone git://git.yoctoproject.org/poky
2. cd poky; git checkout origin/scarthgap

# DOWNLOAD LAYER RASPBERRYPI
3. https://git.yoctoproject.org/meta-raspberrypi
4. cd meta-raspberrypi; git checkout origin/scarthgap

# COMPILE SOURCE CODE
5. source oe-init-build-env
6. vi conf/bblayers.conf
   Add Layer
7. vi conf/local.conf
   Add Drivers
8. bitbake rpi-test-image -c cleanall
9. bitbake rpi-test-image
10. ls -l *.wic*
11. bzip2 -d -f rpi-test-image-raspberrypi3.rootfs.wic.bz2
12. ls -l *.wic*

# FLASH RASPBERRYPI IMAGE
13. ls -l /dev/sda
14. gparted &
15. sudo dd if=rpi-test-image-raspberrypi3.rootfs.wic of=/dev/mmcblk0 status=progress bs=4M
16. /media/ranganadh/boot
17. vi config.txt -> enable uart=1
