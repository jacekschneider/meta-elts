include recipes-core/images/core-image-base.bb
COMPATIBLE_MACHINE = "^rpi$"
SUMMARY = "A console-only image with more full-featured Linux system \
functionality installed."

IMAGE_FEATURES += "splash ssh-server-openssh package-management"

IMAGE_INSTALL = "\
    packagegroup-core-boot \
    packagegroup-core-full-cmdline \
    ${CORE_IMAGE_EXTRA_INSTALL} \
    "

inherit core-image

hostname_pn-base-files = " elts"

IMAGE_INSTALL:append = " os-release"
DISTRO = "ELTS"
DISTRO_NAME = "Embedded Linux Training System"
DISTRO_VERSION = "0.0.1"

EXTRA_IMAGE_FEATURES ?= "debug-tweaks ssh-server-openssh"
IMAGE_INSTALL:append = " gdbserver"
IMAGE_INSTALL:append = " kernel-image kernel-devicetree"
IMAGE_INSTALL:append = " tcpdump iputils "
IMAGE_INSTALL:append = " nano vim"

IMAGE_FSTYPES = "ext4 wic wic.gz"
RPI_USE_U_BOOT = "1"
ENABLE_UART = "1"
ENABLE_I2C = "1"
SDIMG_ROOTFS_TYPE = " ext4"

DISTRO_FEATURES:append = " usrmerge"
DISTRO_FEATURES:append = " systemd"
DISTRO_FEATURES:append = " x11"
DISTRO_FEATURES:append = " desktop"
DISTRO_FEATURES_BACKFILL_CONSIDERED += "sysvinit"
VIRTUAL-RUNTIME_init_manager = "systemd"
VIRTUAL-RUNTIME_initscripts = "systemd-compat-units"

TOOLCHAIN_TARGET_TASK:append = " kernel-devsrc"
IMAGE_INSTALL:append = " kernel-devsrc"

IMAGE_INSTALL:append = " mcpioexpander"
IMAGE_INSTALL:append = " modules-conf"
IMAGE_INSTALL:append = " motd"

SKIP_TFT35_X11_CONFIGS = "1"
IMAGE_INSTALL:append = " lcd-show"
IMAGE_INSTALL:append = " x11-config"

IMAGE_INSTALL:append = " i2c-tools"
IMAGE_INSTALL:append = " spidev-test"
IMAGE_INSTALL:append = " libgpiod libgpiod-tools libiio libcamera"

IMAGE_INSTALL:append = " xserver-xorg xinit xterm openbox matchbox-panel-2 matchbox-desktop"
IMAGE_INSTALL:append = " xf86-video-fbdev"
IMAGE_INSTALL:append = " xf86-video-fbturbo"

IMAGE_INSTALL:append = " packagegroup-core-x11"

RPI_EXTRA_CONFIG = ' \
hdmi_force_hotplug=1 \n \
dtparam=i2c_arm=on \n \
dtparam=spi=on \n \
enable_uart=1 \n \
dtoverlay=tft35a:rotate=90 \n \
dtoverlay=custom-mcp23017 \n \
hdmi_group=2 \n \
hdmi_mode=1 \n \
hdmi_mode=87 \n \
hdmi_cvt 480 320 60 6 0 0 0 \n \
hdmi_drive=2 \n '

IMAGE_INSTALL:append = " python3-spidev"

IMAGE_BOOT_FILES:append = " tft35a.dtbo;overlays/tft35a.dtbo"
IMAGE_BOOT_FILES:append = " custom-mcp23017.dtbo;overlays/custom-mcp23017.dtbo"