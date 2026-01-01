SUMMARY = "Initramfs for Zynq FIT image"
LICENSE = "MIT"

inherit core-image

# Keep it tiny
IMAGE_FEATURES = ""
IMAGE_INSTALL = "busybox base-files base-passwd initramfs-init"

# IMPORTANT: no wic, no sdimg, only initramfs archive
IMAGE_FSTYPES = "cpio.gz"

# Optional: make it clearly “initramfs”
INITRAMFS_SCRIPTS ?= ""
SUMMARY = "Initramfs for Zynq FIT image"
LICENSE = "MIT"
