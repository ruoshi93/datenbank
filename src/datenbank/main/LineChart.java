package datenbank.main;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import datenbank.imdb.*;

public class LineChart {

	String[] xAxis;

	XSSFDrawing drawing;
	XSSFClientAnchor anchor;
	XSSFChart chart;
	
	XDDFValueAxis bottomAxis;
	XDDFValueAxis leftAxis;
	
	XDDFLineChartData data;
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	XDDFCategoryDataSource queries;
	
	public LineChart(String[] xAxis) throws IOException {
		this.xAxis = xAxis;
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Sheet 1");

		// Create a drawing patriarch
		drawing = sheet.createDrawingPatriarch();
		
		// From [0,0] to [7,26]
		anchor = drawing.createAnchor(0, 0, 0, 0, 0, 0, 7, 26);

		chart = drawing.createChart(anchor);
		chart.setTitleText("The runtime of queries in different join order");
		
		bottomAxis = chart.createValueAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle("Query");
		
		leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle("Time");

		data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
		data.setVaryColors(false);
		
		queries = XDDFDataSourcesFactory.fromArray(this.xAxis);
	}

	public void addLine(String title, Long[] yAxis) {
		XDDFNumericalDataSource<Long> runtime = XDDFDataSourcesFactory.fromArray(yAxis);
		
		// Load the data to series
		XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(queries, runtime);
		
		series.setTitle(title, null);
	
		series.setSmooth(false);
		series.setMarkerStyle(MarkerStyle.SQUARE);
		
	}
	
	public void drawLineChart() throws IOException {
		String filePath = "./charts.xlsx";
		File file = new File(filePath);
		OutputStream outputStream = new FileOutputStream(file);

		// Draw the line plot
		chart.plot(data);

		workbook.setActiveSheet(0);
		workbook.write(outputStream);
		outputStream.close();

	}
	
/**
 * 
 * 
	An example of generating a line chart in one of the join orders of query 2. 
	
	
	public static void main(String[] args) throws IOException {
		
		String[] queries = { "1", "2", "3", "4", "5", "total" };
		Long[] runtime = new Long[queries.length];

		LineChart lc = new LineChart(queries);
	
		long startTime;
		long endTime;

		String[] orders = { "12345", "12354", "12435", "12453", "12543", "12534", "13245", "13254", "13425", "13452",
				"13542", "13524", "14325", "14352", "14235", "14253", "14523", "14532", "15342", "15324", "15432",
				"15423", "15243", "15234", "21345", "21354", "21435", "21453", "21543", "21534", "23145", "23154",
				"23415", "23451", "23541", "23514", "24315", "24351", "24135", "24153", "24513", "24531", "25341",
				"25314", "25431", "25413", "25143", "25134", "32145", "32154", "32415", "32451", "32541", "32514",
				"31245", "31254", "31425", "31452", "31542", "31524", "34125", "34152", "34215", "34251", "34521",
				"34512", "35142", "35124", "35412", "35421", "35241", "35214", "42315", "42351", "42135", "42153",
				"42513", "42531", "43215", "43251", "43125", "43152", "43512", "43521", "41325", "41352", "41235",
				"41253", "41523", "41532", "45312", "45321", "45132", "45123", "45213", "45231", "52341", "52314",
				"52431", "52413", "52143", "52134", "53241", "53214", "53421", "53412", "53142", "53124", "54321",
				"54312", "54231", "54213", "54123", "54132", "51342", "51324", "51432", "51423", "51243", "51234" };
		
		DatabaseEngine.cn = new Company_nameTable();
		DatabaseEngine.k = new KeywordTable();
		DatabaseEngine.mc = new Movie_companiesTable();
		DatabaseEngine.mk = new Movie_keywordTable();
		DatabaseEngine.t = new TitleTable();
		
		for (String order : orders) {

			Result result = new Result();
			for (char c : order.toCharArray()) {

				switch (c) {
				case '1':
					startTime = System.currentTimeMillis();
					result = DatabaseEngine.joinExample(result, "cn.id", "mc.company_id");
					endTime = System.currentTimeMillis();
					runtime[0] = endTime - startTime;
					break;
				case '2':
					startTime = System.currentTimeMillis();
					result = DatabaseEngine.joinExample(result, "mc.movie_id", "t.id");
					endTime = System.currentTimeMillis();
					runtime[1] = endTime - startTime;
					break;
				case '3':
					startTime = System.currentTimeMillis();
					result = DatabaseEngine.joinExample(result, "mk.movie_id", "t.id");
					endTime = System.currentTimeMillis();
					runtime[2] = endTime - startTime;
					break;
				case '4':
					startTime = System.currentTimeMillis();
					result = DatabaseEngine.joinExample(result, "mc.movie_id", "mk.movie_id");
					endTime = System.currentTimeMillis();
					runtime[3] = endTime - startTime;
					break;
				case '5':
					startTime = System.currentTimeMillis();
					result = DatabaseEngine.joinExample(result, "mk.keyword_id", "k.id");
					endTime = System.currentTimeMillis();
					runtime[4] = endTime - startTime;
					break;
				default:
					System.out.println("Error: The corresponding execution does not exist. ");
					break;
				}
			}
			runtime[5] = runtime[0] + runtime[1] + runtime[2] + runtime[3] + runtime[4];
			lc.addLine(order, runtime);

		}

		lc.drawLineChart();	
		

	}

*/
	
}
