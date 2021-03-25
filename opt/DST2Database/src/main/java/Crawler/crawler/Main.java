package Crawler.crawler;
//可以用windows定时任务器设置每周执行jar包!!!
public class Main {
    public static void main(String[] args) {

        DrugLabelCrawler drugLabelCrawler = new DrugLabelCrawler();
        DosingGuidelineCrawler dosingGuidelineCrawler = new DosingGuidelineCrawler();


        // Step 1
        drugLabelCrawler.doCrawlerDrug();

        // Step 2
        drugLabelCrawler.doCrawlerDrugLabel();

        //step3
        dosingGuidelineCrawler.doCrawlerDosingGuidelineList();


    }
}
