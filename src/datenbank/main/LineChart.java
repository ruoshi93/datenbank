package datenbank.main;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Date;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFCreationHelper;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFFormulaEvaluator;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xddf.usermodel.PresetLineDash;
import org.apache.poi.xddf.usermodel.XDDFLineProperties;
import org.apache.poi.xddf.usermodel.XDDFPresetLineDash;
import org.apache.poi.xddf.usermodel.chart.*;
import org.apache.poi.xssf.usermodel.XSSFChart;
import org.apache.poi.xssf.usermodel.XSSFClientAnchor;
import org.apache.poi.xssf.usermodel.XSSFDrawing;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class LineChart {

	String[] xAxis;

	XSSFDrawing drawing;
	XSSFClientAnchor anchor;
	XSSFChart chart;
	
	// 分类轴标(X轴),标题位置
	XDDFValueAxis bottomAxis;
	// 值(Y轴)轴,标题位置
	XDDFValueAxis leftAxis;
	
	XDDFLineChartData data;
	
	XSSFWorkbook workbook;
	XSSFSheet sheet;
	
	XDDFCategoryDataSource queries;
	
	public LineChart(String[] xAxis) throws IOException {
		this.xAxis = xAxis;
		
		workbook = new XSSFWorkbook();
		sheet = workbook.createSheet("Sheet 1");

		// create a drawing patriarch
		drawing = sheet.createDrawingPatriarch();
		
		// 前四个默认0，[0,5]：从0列5行开始;[7,26]:到7列26行结束
		// 默认宽度(14-8)*12
		anchor = drawing.createAnchor(0, 0, 0, 0, 0, 0, 7, 26);

		chart = drawing.createChart(anchor);
		chart.setTitleText("The runtime of queries in different join order");
		
		// 分类轴标(X轴),标题位置
		bottomAxis = chart.createValueAxis(AxisPosition.BOTTOM);
		bottomAxis.setTitle("Query");
		
		// 值(Y轴)轴,标题位置
		leftAxis = chart.createValueAxis(AxisPosition.LEFT);
		leftAxis.setTitle("Time");

		// LINE：折线图
		data = (XDDFLineChartData) chart.createData(ChartTypes.LINE, bottomAxis, leftAxis);
		data.setVaryColors(false);
		
		queries = XDDFDataSourcesFactory.fromArray(this.xAxis);
	}

	public void addLine(String title, Long[] yAxis) {
		XDDFNumericalDataSource<Long> runtime = XDDFDataSourcesFactory.fromArray(yAxis);
		
		// 图表加载数据，折线1
		XDDFLineChartData.Series series = (XDDFLineChartData.Series) data.addSeries(queries, runtime);
		
		series.setTitle(title, null);
	
		series.setSmooth(false);
		series.setMarkerStyle(MarkerStyle.SQUARE);
		
	}
	
	public void drawLineChart() throws IOException {
		String filePath = "/Users/lili/Documents/Bachelor Thesis/charts.xls";
		File file = new File(filePath);
		OutputStream outputStream = new FileOutputStream(file);

		// draw the line plot
		chart.plot(data);

		workbook.setActiveSheet(0);
		workbook.write(outputStream);
		outputStream.close();

	}
	
	public static void main(String[] args) throws IOException {
		LineChart lc = new LineChart(new String[] {"1","2","3","4","5"});
		
		lc.addLine("12345", new Long[] {1824L,990L,5663L,71901L,3885L});
		lc.addLine("23451", new Long[] {4396L,1337L,5222L,45053L,6938L});
		lc.drawLineChart();

	}
	
}
