args <- commandArgs(trailingOnly = TRUE)
library(VennDiagram)
locfile <- read.table(file=args[1],header = FALSE)
names(locfile) <- c('sig','caller')
allcaller<-unique(locfile[[2]])
loclist<-as.list(vector(length = length(allcaller)))
for (n in 1:length(allcaller)){
  loclist[n]<-locfile[locfile$caller==allcaller[n],]
}
names(loclist)<-allcaller
venn.diagram(loclist[c("varscan-qbrc.indels","ms.indels")],height=5000,width=5200,resolution=500,imagetype="png",filename=paste(args[2],"_indels_venn.png"))
venn.diagram(loclist[c("lofreq-call","mutect2","shimmer","mutect1","varscan-qbrc.snvs")],height=5000,width=5200,resolution=500,imagetype="png",filename=paste(args[2],"_snvs_venn.png"))
