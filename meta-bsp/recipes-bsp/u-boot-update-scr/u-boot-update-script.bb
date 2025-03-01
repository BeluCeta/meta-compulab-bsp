LICENSE = "GPLv2+"
LIC_FILES_CHKSUM = "file://${WORKDIR}/README;md5=dfca3ff67e8e1b399109eb93e66b3465"

FILESEXTRAPATHS:prepend := "${THISDIR}/${PN}:"

DEPENDS = " u-boot-mkimage-native "

SRC_URI = "\
    file://boot.script \
    file://README \
"

do_compile:imx-boot-container() {
    BOOTLOADER=$(basename $(ls ${WORKDIR}/recipe-sysroot/boot/imx-boot-* | head -1))
    sed "s|##BOOTLOADER##|${BOOTLOADER}|" ${WORKDIR}/boot.script > ${WORKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${WORKDIR}/boot.update.in ${WORKDIR}/boot.update.scr
}

# To fix the machineoverrides for imx7 machines
do_compile:compulab-mx7() {
    cp ${WORKDIR}/boot.script ${WORKDIR}/boot.update.in
    mkimage -C none -A arm -T script -d ${WORKDIR}/boot.script ${WORKDIR}/boot.update.scr
}

inherit deploy

do_deploy() {
    install -d ${DEPLOYDIR}
    install -m 0644 ${WORKDIR}/boot.update.scr ${DEPLOYDIR}
}

do_install() {
    install -d ${D}/boot
    install -m 0644 ${WORKDIR}/boot.update.scr ${D}/boot/
    install -m 0644 ${WORKDIR}/boot.update.in ${D}/boot/
}

addtask do_deploy after do_compile before do_build

FILES:${PN} += "/boot/"

RPROVIDES:${PN} += "u-boot-update-script"

PACKAGE_ARCH = "${MACHINE_ARCH}"

DEPENDS:append:imx-boot-container = "imx-boot"
