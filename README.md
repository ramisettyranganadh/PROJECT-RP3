
# PROJECT27-REPOSITORY

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
