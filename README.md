
# PROJECT27-REPOSITORY

https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html
Setup: https://www.youtube.com/watch?v=VH4y-wBOCPk
SSH Remote: https://www.youtube.com/watch?v=ltckiBV9FXg

1. source oe-init-build-env
2. vi conf/bblayers.conf
   Add Layer
3. vi conf/local.conf
   Add Drivers
4. bitbake rpi-test-image -c cleanall
5. bitbake rpi-test-image
6. ls -l *.wic*
7. bzip2 -d -f rpi-test-image-raspberrypi3.rootfs.wic.bz2
8. ls -l *.wic*
9. ls -l /dev/sda
10. gparted &
11. sudo dd if=rpi-test-image-raspberrypi3.rootfs.wic of=/dev/mmcblk0 status=progress bs=4M
12. /media/ranganadh/boot
13. vi config.txt -> enable uart=1
