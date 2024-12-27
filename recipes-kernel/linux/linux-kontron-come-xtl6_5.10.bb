# linux-kontron-come-xtl6.bb:

FILESEXTRAPATHS:prepend = "${THISDIR}/linux-kontron-come-xtl6:"
LINUX_VERSION_EXTENSION = "-xtl6"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.10.83"
LINUX_KERNEL_TYPE = "standard"

# This kernel uses the Intel-LTS kernel repository
KREPO = "git://github.com/intel/linux-intel-lts.git;protocol=https"
KBRANCH = "5.10/yocto"
KHASH ?= "9f07b543198de80a3195a85a80c1adf6666936bf"

# Alternative Yocto standard/base kernel repository
# Note: Untested! Provided patches might not cleanly apply
#KREPO = "git://git.yoctoproject.org/linux-yocto;protocol=git"
#KBRANCH = "v5.10/standard/base"
#KHASH ?= "${AUTOREV}"

KMETA = "kernel-meta"
KMETA_BRANCH = "yocto-5.10"
KMETA_SRC = "git://git.yoctoproject.org/yocto-kernel-cache"
KMETA_HASH ?= "413b7202686fc4f2c88aa4915547bc184b267422"
# Uncomment this to automatically use the newest available commit
#KMETA_HASH = "${AUTOREV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRCREV_machine:pn-linux-kontron-come-xtl6 ?= "${KHASH}"
SRCREV_meta:pn-linux-kontron-come-xtl6 ?= "${KMETA_HASH}"

SRC_URI = "${KREPO};name=machine;branch=${KBRANCH};bareclone=1 \
           ${KMETA_SRC};type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI += "file://kontron-come-xtl6-64-standard.scc \
            file://kontron-come-xtl6-64.scc \
            file://kontron-come-xtl6.cfg \
           "

PV = "${LINUX_VERSION}+git${SRCPV}"

INCLUDE_PATCHES := "${THISDIR}/linux-kontron-come-xtl6/patches-5.10"

include linux-patches.inc

COMPATIBLE_MACHINE = "kontron-come-xtl6-64"

include linux-common.inc
