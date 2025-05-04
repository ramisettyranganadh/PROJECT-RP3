SUMMARY = "Raspberry Pi 3 Custom Image with WiFi Configuration"
DESCRIPTION = "An image for Raspberry Pi 3 with WiFi auto-login and essential packages"
LICENSE = "MIT"

inherit core-image

# Image features for SSH and debugging
IMAGE_FEATURES += "ssh-server-openssh tools-debug"

# Add packages to the image
IMAGE_INSTALL += "packagegroup-core-boot wpa-supplicant openssh nano vim linux-firmware"

# Append WiFi-related packages
#IMAGE_INSTALL:append = " linux-firmware-rpi-b wlan-fw"

# Add custom WiFi configuration
SRC_URI += "file://wpa_supplicant.conf"

do_install:append() {
    install -D ${WORKDIR}/wpa_supplicant.conf ${D}/etc/wpa_supplicant.conf
}
