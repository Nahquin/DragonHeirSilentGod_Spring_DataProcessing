import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Arrays;
/*LINKS
 * https://nanonets.com/blog/java-web-scraping-tutorial/
 * https://blog.fileformat.com/spreadsheet/add-image-in-excel-cell-with-java-poi/
 * https://stackoverflow.com/questions/5528388/java-fileinputstream-using-with-url
 * https://poi.apache.org/apidocs/dev/org/apache/poi/xssf/usermodel/
 * https://www.baeldung.com/java-apache-poi-merge-cells
 * LINKS*/

public class SaveToExcel {
    static XSSFWorkbook workbook = new XSSFWorkbook();
    static Sheet sheet = workbook.createSheet("Heros");
    static CellStyle cs = workbook.createCellStyle();
    static String url = "https://www.dragonheir.info/heroes/";
    static String[] heroData = new String[7];


    public SaveToExcel() throws IOException {
        cs.setWrapText(true);   //Wrapping text

        int a = 0;
        Elements allLinkFromSite = myReader(url, "img[src]");
        excelFromat(a, (allLinkFromSite.size() - 1) / 2);
        excelDataFill(allLinkFromSite);

    }


    private static void excelDataFill(Elements allLinkFromSite) throws IOException {

        int heroRow = 0;
        for (Element link : allLinkFromSite) {
            Row row = sheet.createRow(heroRow);

            if (link.toString().contains("/img/hero/pic_hero_show")) {

                heroDataCollector(link);

                cellCreatorSetter(heroRow, 0, heroData[1]);
                cellCreatorSetter(heroRow + 1, 3, heroData[3]);//Ultimate
                cellCreatorSetter(heroRow + 2, 3, heroData[4]);//Battle Skill
                cellCreatorSetter(heroRow + 3, 3, heroData[5]);//Passive Skill

                pictureToWorkbook(heroData[0], heroRow);


                System.out.println(heroRow/4+1);
                heroRow = heroRow + 4;
                Arrays.fill(heroData, null);
                saveToExcel();
            }
        }
        //sheet.autoSizeColumn(j);
    }

    private static Cell cellCreator(int rowNumber, int cellNumber) {
        if (sheet.getRow(rowNumber) == null) {
            sheet.createRow(rowNumber);
        }
        return sheet.getRow(rowNumber).createCell(cellNumber);
    }

    private static Cell cellGetter(int rowNumber, int cellNumber) {
        Cell cell = sheet.getRow(rowNumber).getCell(cellNumber);
        return cell;
    }

    private static void cellCreatorSetter(int rowNumber, int cellNumber, String text) {
        Cell cellActual = cellCreator(rowNumber, cellNumber);
        cellActual.setCellStyle(cs);
        cellActual.getCellStyle().setAlignment(HorizontalAlignment.CENTER);
        cellActual.getCellStyle().setVerticalAlignment(VerticalAlignment.CENTER);
        cellActual.setCellValue(text);

    }

    private static void excelFromat(int row, int heroNumbers) {

        int columnMultiplicator = 270;
        sheet.setColumnWidth(0, 10 * columnMultiplicator);//1.29 (az excel mérete*387.5968992248062 az itt megadandó érték)
        sheet.setColumnWidth(1, 10 * columnMultiplicator);
        sheet.setColumnWidth(2, 10 * columnMultiplicator);
        sheet.setColumnWidth(3, 110 * columnMultiplicator);

        for (int i = 0; i < heroNumbers; i = i + 4) {
            cellCreatorSetter(i, 0, "name");
            cellCreatorSetter(i + 1, 2, "U");
            cellCreatorSetter(i + 2, 2, "A");
            cellCreatorSetter(i + 3, 2, "P");

            sheet.getRow(i).setHeight(Short.parseShort("300"));//15
            sheet.getRow(i + 1).setHeight(Short.parseShort("1000"));//50 (az excel mérete*20 az itt megadandó érték)
            sheet.getRow(i + 2).setHeight(Short.parseShort("1000"));
            sheet.getRow(i + 3).setHeight(Short.parseShort("1000"));

            sheet.addMergedRegion(new CellRangeAddress(i, i, 0, 3));//name frame
            sheet.addMergedRegion(new CellRangeAddress(i + 1, i + 3, 0, 1));//picture frame

        }
    }

    private static void pictureToWorkbook(String pictureUrl, int row) throws IOException {
        URL picUrl = new URL(pictureUrl);
        InputStream is = picUrl.openStream();
        byte[] bytes = IOUtils.toByteArray(is);
        int pictureId = workbook.addPicture(bytes, Workbook.PICTURE_TYPE_PNG);
        is.close();
        XSSFCreationHelper helper = workbook.getCreationHelper();
        XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
        XSSFClientAnchor anchor = helper.createClientAnchor();
        anchor.setCol1(0);
        anchor.setCol2(2);
        anchor.setRow1(row + 1);
        anchor.setRow2(row + 4);
        anchor.setAnchorType(ClientAnchor.AnchorType.DONT_MOVE_AND_RESIZE);
        Picture pict = drawing.createPicture(anchor, pictureId);
        pict.resize(1, 1);
//auto-size picture relative to its top-left corner
        String file = "picture.xls";
        if (workbook instanceof XSSFWorkbook) file += "x";
    }

    private static void saveToExcel() throws IOException {
        try (FileOutputStream saveExcel = new FileOutputStream("target/DragonHeirHeros.xlsx")) {
            workbook.write(saveExcel);
        }
    }

    public static Elements myReader(String dataUrl, String dataSpecification) throws IOException {
        URL obj = new URL(dataUrl);
        HttpURLConnection con = (HttpURLConnection) obj.openConnection();
        con.setRequestProperty("User-Agent", "Mozilla/5.0");
        int responseCode = con.getResponseCode();
        BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
        String inputLine;
        StringBuilder response = new StringBuilder();
        while ((inputLine = in.readLine()) != null) {
            response.append(inputLine);
        }
        in.close();
        String html = response.toString();
        Document doc = Jsoup.parse(html);
        Elements links = doc.select(dataSpecification);
        return links;
    }

    public static void heroDataCollector(Element choppAbleText) throws IOException {
        String[] splitter;
        String[] splitter2;
        splitter = choppAbleText.toString().split("\"");

        heroData[0] = splitter[1];//picture link
        heroData[1] = splitter[3];//name
        splitter2 = splitter[1].split("[_.]");
        heroData[2] = splitter2[splitter2.length - 2];//id

        Elements skillsText = myReader(url + heroData[2], "div[class=\"wztext\"]");
        String replaced = skillsText.toString().replace("<div class=\"wztext\" data-v-6c9f9a3f>\n ", "");
        String[] out = replaced.split("\n</div>\n");

        for (int i = 0; i < out.length; i++) {//skillek
            heroData[3 + i] = out[i];
        }
    }
}
