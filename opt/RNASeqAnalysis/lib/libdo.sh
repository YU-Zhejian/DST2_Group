#!/usr/bin/env bash
# LIBDO V2
# From https://github.com/YuZJLab/LinuxMiniPrograms, commit  19ec030cdc2dee900577e4df620b2b3b6bfb5a2d, branch BSD
function DO_ECHO() {
	[ "${LIBDO_LOG_MODE:-}" = "S" ] && retur || true
	! [ -z "${LIBDO_LOG}" ] && echo "${@}" >>"${LIBDO_LOG}" || echo -e "\033[33m${*}\033[0m" >&2
}
function DO() {
	local LIBDO_CMD="${@}"
	DO_ECHO "LIBDO IS GOING TO EXECUTE ${LIBDO_CMD}"
	DO_ECHO "LIBDO STARTED AT $(date "+%Y-%m-%d %H:%M:%S")"
	local LIBDO_PID
	if [ -z "${LIBDO_LOG:-}" ]; then
		eval ${LIBDO_CMD} &
		LIBDO_PID=${!}
	else
		case "${LIBDO_LOG_MODE:-}" in
		"2")
			eval ${LIBDO_CMD} 2>>"${LIBDO_LOG}" &
			LIBDO_PID=${!}
			;;
		"3")
			eval ${LIBDO_CMD} >>"${LIBDO_LOG}" &
			LIBDO_PID=${!}
			;;
		"4")
			eval ${LIBDO_CMD} &>>"${LIBDO_LOG}" &
			LIBDO_PID=${!}
			;;
		*)
			eval ${LIBDO_CMD} &
			LIBDO_PID=${!}
			;;
		esac
	fi
	[ -z "${LIBDO_TOP_PID:-}" ] && DO_ECHO "LIBDO PID ${LIBDO_PID}" || DO_ECHO "LIBDO PID ${LIBDO_PID} with top_pid ${LIBDO_TOP_PID}"
	wait ${LIBDO_PID} && LIBDO_PRIV=0 || LIBDO_PRIV=${?}
	DO_ECHO "LIBDO STOPPED AT $(date "+%Y-%m-%d %H:%M:%S")"
	if [ ${LIBDO_PRIV} -ne 0 ]; then
		DO_ECHO "LIBDO FAILED, GOT \$?=${LIBDO_PRIV}"
		if ! [ -z ${LIBDO_TOP_PID:-} ]; then
			DO_ECHO "LIBDO WILL KILL ${LIBDO_TOP_PID}"
			kill -9 ${LIBDO_TOP_PID}
		fi
	else
		DO_ECHO "LIBDO EXITED SUCCESSFULLY"
	fi
	return ${LIBDO_PRIV}
}
