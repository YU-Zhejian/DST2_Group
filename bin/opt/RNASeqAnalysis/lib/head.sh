#!/usr/bin/env bash
set -ue
. "${LIBDO_PATH}"
TARGET=TARGET
WD=WD
LIBDO_LOG_MODE=4
LIBDO_TOP_PID=${$}
LIBDO_LOG="${WD}/${TARGET}_$(date +%Y-%m-%d_%H-%M-%S).log"
