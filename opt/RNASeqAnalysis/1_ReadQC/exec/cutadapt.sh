args=(${TARGET})
fn="../0_DataRetrival/${args[0]}.fq.gz"
[ -f "${fn}" ] && [ ! -f "${args[0]}.fq.gz" ]
LIBDO_LOG="${args[0]}_$(date +%Y-%m-%d_%H-%M-%S).log"
if [ ! -z "${args[2]:-}" ]; then
	DO cutadapt "${fn}" -u "${args[1]}" -u "${args[2]}" -o "${args[0]}.fq.gz"
else
	DO cutadapt "${fn}" -u "${args[1]}" -o "${args[0]}".fq.gz
fi
