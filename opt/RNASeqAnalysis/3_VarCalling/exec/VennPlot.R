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
venn.diagram(loclist,height=5000,width=5200,resolution=500,imagetype="png",filename=paste(args[2],"_snps_venn.png"))
