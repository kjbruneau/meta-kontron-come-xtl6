# linux-kontron-come-xtl6-rt.bb:

FILESEXTRAPATHS:prepend = "${THISDIR}/linux-kontron-come-xtl6:"
LINUX_VERSION_EXTENSION = "-xtl6"

require recipes-kernel/linux/linux-yocto.inc

LINUX_VERSION ?= "5.10.83"
LINUX_KERNEL_TYPE = "preempt-rt"

# This kernel uses the Intel-LTS kernel repository
KREPO = "git://github.com/intel/linux-intel-lts.git;protocol=https"
KBRANCH = "5.10/preempt-rt"
KHASH ?= "cb966070f7c10e4a8e02c3fc46152bb9519e51bc"
# Uncomment this to automatically use the newest available commit
#KHASH = "${AUTOREV}"

# Alternative Yocto standard/base kernel repository
# Note: Untested! Provided patches might not cleanly apply
#KREPO = "git://git.yoctoproject.org/linux-yocto;protocol=git"
#KBRANCH = "v5.10/standard/preempt-rt/base"
#KHASH ?= "${AUTOREV}"

KMETA = "kernel-meta"
KMETA_BRANCH = "yocto-5.10"
KMETA_SRC = "git://git.yoctoproject.org/yocto-kernel-cache"
KMETA_HASH ?= "413b7202686fc4f2c88aa4915547bc184b267422"
# Uncomment this to automatically use the newest available commit
#KMETA_HASH = "${AUTOREV}"

LIC_FILES_CHKSUM = "file://COPYING;md5=6bc538ed5bd9a7fc9398086aedcd7e46"

SRCREV_machine:pn-linux-kontron-come-xtl6-rt ?= "${KHASH}"
SRCREV_meta:pn-linux-kontron-come-xtl6-rt ?= "${KMETA_HASH}"

SRC_URI = "${KREPO};name=machine;branch=${KBRANCH};bareclone=1 \
           ${KMETA_SRC};type=kmeta;name=meta;branch=${KMETA_BRANCH};destsuffix=${KMETA}"

SRC_URI += "file://kontron-come-xtl6-64-preempt-rt.scc \
            file://kontron-come-xtl6-64.scc \
            file://kontron-come-xtl6.cfg \
           "

PV = "${LINUX_VERSION}+git${SRCPV}"

INCLUDE_PATCHES := "${THISDIR}/linux-kontron-come-xtl6/patches-5.10"

include linux-patches.inc

# Add RT-only patches
INCLUDE_RTPATCHES := "${THISDIR}/linux-kontron-come-xtl6/patches-5.10_rtonly"
FILESEXTRAPATHS:prepend := "${INCLUDE_RTPATCHES}:"
SRC_URI:append := " ${@__find_patches("${INCLUDE_RTPATCHES}")}"

COMPATIBLE_MACHINE = "kontron-come-xtl6-64"

include linux-common.inc
