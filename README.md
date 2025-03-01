
# PROJECT27-REPOSITORY

https://docs.yoctoproject.org/brief-yoctoprojectqs/index.html



Setup: https://www.youtube.com/watch?v=VH4y-wBOCPk

SSH Remote: https://www.youtube.com/watch?v=ltckiBV9FXg

1. https://git.yoctoproject.org/meta-raspberrypi
2. git checkout origin/scarthgap
3. source oe-init-build-env
4. vi conf/bblayers.conf
   Add Layer
5. vi conf/local.conf
   Add Drivers
6. bitbake rpi-test-image -c cleanall
7. bitbake rpi-test-image
8. ls -l *.wic*
9. bzip2 -d -f rpi-test-image-raspberrypi3.rootfs.wic.bz2
10. ls -l *.wic*
11. ls -l /dev/sda
12. gparted &
13. sudo dd if=rpi-test-image-raspberrypi3.rootfs.wic of=/dev/mmcblk0 status=progress bs=4M
14. /media/ranganadh/boot
15. vi config.txt -> enable uart=1
