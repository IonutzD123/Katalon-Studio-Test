package helpers

import static com.kms.katalon.core.checkpoint.CheckpointFactory.findCheckpoint
import static com.kms.katalon.core.testcase.TestCaseFactory.findTestCase
import static com.kms.katalon.core.testdata.TestDataFactory.findTestData
import static com.kms.katalon.core.testobject.ObjectRepository.findTestObject
import static com.kms.katalon.core.testobject.ObjectRepository.findWindowsObject

import com.kms.katalon.core.annotation.Keyword
import com.kms.katalon.core.checkpoint.Checkpoint
import com.kms.katalon.core.cucumber.keyword.CucumberBuiltinKeywords as CucumberKW
import com.kms.katalon.core.mobile.keyword.MobileBuiltInKeywords as Mobile
import com.kms.katalon.core.model.FailureHandling
import com.kms.katalon.core.testcase.TestCase
import com.kms.katalon.core.testdata.TestData
import com.kms.katalon.core.testng.keyword.TestNGBuiltinKeywords as TestNGKW
import com.kms.katalon.core.testobject.TestObject
import com.kms.katalon.core.webservice.keyword.WSBuiltInKeywords as WS
import com.kms.katalon.core.webui.keyword.WebUiBuiltInKeywords as WebUI
import com.kms.katalon.core.windows.keyword.WindowsBuiltinKeywords as Windows
import java.io.File;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import org.w3c.dom.Attr;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import internal.GlobalVariable

public class CreateDataFile {


	public static final String xmlFilePath = "Data Files/Client/Startups.dat";

	public static String createRandomStartupName() {
		final String lexicon = "ABCDEFGHIJKLMNOPQRSTUVWXYZ12345674890";
		final java.util.Random rand = new java.util.Random();
		final Set<String> identifiers = new HashSet<String>();

		StringBuilder builder = new StringBuilder();
		while(builder.toString().length() == 0) {
			int length = rand.nextInt(5)+5;
			for(int i = 0; i < length; i++) {
				builder.append(lexicon.charAt(rand.nextInt(lexicon.length())));
			}
			if(identifiers.contains(builder.toString())) {
				builder = new StringBuilder();
			}
		}
		return builder.toString();
	}

	public static List<String> startupsList(){
		List<String> startups = new ArrayList<String>()
		startups.add(createRandomStartupName())
		startups.add(createRandomStartupName())
		startups.add(createRandomStartupName())
		startups.add(createRandomStartupName())
		return startups
	}
	
	public static void writeDataFile(){

		try {
			DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();

			DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

			Document document = documentBuilder.newDocument();

			// root element
			Element root = document.createElement("DataFileEntity");
			document.appendChild(root);

			// employee element
			Element description = document.createElement("description");
			description.appendChild(document.createTextNode(" "))
			root.appendChild(description);

			Element name = document.createElement("name");
			name.appendChild(document.createTextNode("Startups"))
			root.appendChild(name);

			Element tag = document.createElement("tag");
			tag.appendChild(document.createTextNode(" "))
			root.appendChild(tag);

			Element containsHeaders = document.createElement("containsHeaders");
			containsHeaders.appendChild(document.createTextNode("true"))
			root.appendChild(containsHeaders);

			Element csvSeperator = document.createElement("csvSeperator");
			csvSeperator.appendChild(document.createTextNode(" "))
			root.appendChild(csvSeperator);

			Element dataFileGUID = document.createElement("dataFileGUID");
			dataFileGUID.appendChild(document.createTextNode(" "))
			root.appendChild(dataFileGUID);

			Element dataSourceUrl = document.createElement("dataSourceUrl");
			dataSourceUrl.appendChild(document.createTextNode(" "))
			root.appendChild(dataSourceUrl);

			Element driver = document.createElement("driver");
			driver.appendChild(document.createTextNode("InternalData"))
			root.appendChild(driver);

			// element data
			Element data = document.createElement("data");		
			data.appendChild(document.createTextNode("Startups" +  " "
					+ startupsList().get(0) + " "  + startupsList().get(1) + " " + startupsList().get(2) + " " + startupsList().get(3)))

			root.appendChild(data);
			
			// element data1
			Element data1 = document.createElement("data");
			data1.appendChild(document.createTextNode("Taglines" +  " "
					+ "Inovative" + " "  + "Creative" + " " + "Simple" + " " + "Skilled"))

			root.appendChild(data1);
			
			//-------------------------------------------------------------------------------------------------------
			// Column 1
			Element internalDataColumns = document.createElement("internalDataColumns");

			Element columnIndex = internalDataColumns.appendChild(document.createElement("columnIndex"))
			columnIndex.appendChild(document.createTextNode("0"))
			internalDataColumns.appendChild(columnIndex)

			Element name2 = internalDataColumns.appendChild(document.createElement("name"))
			name2.appendChild(document.createTextNode("Names"))
			internalDataColumns.appendChild(name2)

			Element size = internalDataColumns.appendChild(document.createElement("size"))
			size.appendChild(document.createTextNode("1"))
			internalDataColumns.appendChild(size)

			root.appendChild(internalDataColumns);

			// Column 2
			Element internalDataColumns1 = document.createElement("internalDataColumns");

			Element columnIndex1 = internalDataColumns1.appendChild(document.createElement("columnIndex"))
			columnIndex1.appendChild(document.createTextNode("1"))
			internalDataColumns1.appendChild(columnIndex1)

			Element name3 = internalDataColumns1.appendChild(document.createElement("name"))
			name3.appendChild(document.createTextNode("Startup1"))
			internalDataColumns1.appendChild(name3)

			Element size1 = internalDataColumns1.appendChild(document.createElement("size"))
			size1.appendChild(document.createTextNode("1"))
			internalDataColumns1.appendChild(size1)

			root.appendChild(internalDataColumns1);

			// Column 3
			Element internalDataColumns2 = document.createElement("internalDataColumns");

			Element columnIndex2 = internalDataColumns2.appendChild(document.createElement("columnIndex"))
			columnIndex2.appendChild(document.createTextNode("2"))
			internalDataColumns2.appendChild(columnIndex2)

			Element name4 = internalDataColumns2.appendChild(document.createElement("name"))
			name4.appendChild(document.createTextNode("Startup2"))
			internalDataColumns2.appendChild(name4)

			Element size2 = internalDataColumns2.appendChild(document.createElement("size"))
			size2.appendChild(document.createTextNode("1"))
			internalDataColumns2.appendChild(size2)

			root.appendChild(internalDataColumns2);

			// Column 4

			Element internalDataColumns3 = document.createElement("internalDataColumns");

			Element columnIndex3 = internalDataColumns3.appendChild(document.createElement("columnIndex"))
			columnIndex3.appendChild(document.createTextNode("3"))
			internalDataColumns3.appendChild(columnIndex3)

			Element name5 = internalDataColumns3.appendChild(document.createElement("name"))
			name5.appendChild(document.createTextNode("Startup3"))
			internalDataColumns3.appendChild(name5)

			Element size3 = internalDataColumns3.appendChild(document.createElement("size"))
			size3.appendChild(document.createTextNode("1"))
			internalDataColumns3.appendChild(size3)

			root.appendChild(internalDataColumns3);


			// Column 5

			Element internalDataColumns4 = document.createElement("internalDataColumns");

			Element columnIndex4 = internalDataColumns4.appendChild(document.createElement("columnIndex"))
			columnIndex4.appendChild(document.createTextNode("4"))
			internalDataColumns4.appendChild(columnIndex4)

			Element name6 = internalDataColumns4.appendChild(document.createElement("name"))
			name6.appendChild(document.createTextNode("Startup4"))
			internalDataColumns4.appendChild(name6)

			Element size4 = internalDataColumns4.appendChild(document.createElement("size"))
			size4.appendChild(document.createTextNode("1"))
			internalDataColumns4.appendChild(size4)

			root.appendChild(internalDataColumns4);

			//----------------------------------------------------------------------------------------------------------

			Element isInternalPath = document.createElement("isInternalPath");
			isInternalPath.appendChild(document.createTextNode("false"))
			root.appendChild(isInternalPath);

			Element query = document.createElement("query");
			query.appendChild(document.createTextNode(" "))
			root.appendChild(query);

			Element secureUserAccount = document.createElement("secureUserAccount");
			secureUserAccount.appendChild(document.createTextNode("false"))
			root.appendChild(secureUserAccount);

			Element sheetName = document.createElement("sheetName");
			sheetName.appendChild(document.createTextNode(" "))
			root.appendChild(sheetName);

			Element usingGlobalDBSetting = document.createElement("usingGlobalDBSetting");
			usingGlobalDBSetting.appendChild(document.createTextNode("false"))
			root.appendChild(usingGlobalDBSetting);

			// create the xml file
			//transform the DOM Object to an XML File
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			DOMSource domSource = new DOMSource(document);
			StreamResult streamResult = new StreamResult(new File(xmlFilePath).getAbsolutePath());

			// If you use
			// StreamResult result = new StreamResult(System.out);
			// the output will be pushed to the standard output ...
			// You can use that for debugging

			transformer.transform(domSource, streamResult);

			println("Done creating XML File");

		} catch (ParserConfigurationException pce) {
			pce.printStackTrace();
		} catch (TransformerException tfe) {
			tfe.printStackTrace();
		}
	}
}
