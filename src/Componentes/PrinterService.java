/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Componentes;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.print.PageFormat;
import java.awt.print.Printable;
import java.awt.print.PrinterException;
import java.io.FileInputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
 
import javax.print.Doc;
import javax.print.DocFlavor;
import javax.print.DocPrintJob;
import javax.print.PrintException;
import javax.print.PrintService;
import javax.print.PrintServiceLookup;
import javax.print.SimpleDoc;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.PrintRequestAttributeSet;
 
public class PrinterService implements Printable {
	
	public List<String> getPrinters(){
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		
		PrintService printServices[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		
		List<String> printerList = new ArrayList<String>();
		for(PrintService printerService: printServices){
			printerList.add( printerService.getName());
		}
		
		return printerList;
	}
 
	@Override
	public int print(Graphics g, PageFormat pf, int page)
			throws PrinterException {
		if (page > 0) { /* We have only one page, and 'page' is zero-based */
			return NO_SUCH_PAGE;
		}
 
		/*
		 * User (0,0) is typically outside the imageable area, so we must
		 * translate by the X and Y values in the PageFormat to avoid clipping
		 */
		Graphics2D g2d = (Graphics2D) g;
		g2d.translate(pf.getImageableX(), pf.getImageableY());
		/* Now we perform our rendering */
 
		g.setFont(new Font("Roman", 0, 8));
		g.drawString("Hello world !", 0, 10);
 
		return PAGE_EXISTS;
	}
 
	/*public void printString(String printerName, String text) {
		
		// find the printService of name printerName
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
		PrintService service = findPrintService(printerName, printService);
 
		DocPrintJob job = service.createPrintJob();
 
		try {
 
			byte[] bytes;
 
			// important for umlaut chars
			bytes = text.getBytes("CP437");
 
			Doc doc = new SimpleDoc(bytes, flavor, null);
 
			
			job.print(doc, null);
 
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
 
	}
 
	public void printBytes(String printerName, byte[] bytes) {
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
 
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		PrintService service = findPrintService(printerName, printService);
 
		DocPrintJob job = service.createPrintJob();
 
		try {
 
			Doc doc = new SimpleDoc(bytes, flavor, null);
 
			job.print(doc, null);
 
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	private PrintService findPrintService(String printerName,
			PrintService[] services) {
		for (PrintService service : services) {
			if (service.getName().equalsIgnoreCase(printerName)) {
				return service;
			}
		}
 
		return null;
	}*/
        
        private PrintService findPrintService(String printerName,
			PrintService[] services) {
		for (PrintService service : services) {
			if (service.getName().equalsIgnoreCase(printerName)) {
				return service;
			}
		}
 
		return null;
	}
        
        public void printString(String text) {
		
		// find the printService of name printerName
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();                
                PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
                if (defaultPrintService != null) {
                    DocPrintJob printJob = defaultPrintService.createPrintJob();
                    try {
                        byte[] bytes;
                        // important for umlaut chars
                        bytes = text.getBytes("CP437");
                        Doc doc = new SimpleDoc(bytes, flavor, null);
                        printJob.print(doc, pras);
                    } catch (UnsupportedEncodingException | PrintException ex) {
                        Mensajes.error("Error printString: "+ex.getMessage());
                    }
                } else {
                    Mensajes.error("No existen impresoras instaladas");
                } 
        }
 
	public void printBytes(byte[] bytes) {
            DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
            PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
            PrintService defaultPrintService = PrintServiceLookup.lookupDefaultPrintService();
            if (defaultPrintService != null) {
                DocPrintJob job = defaultPrintService.createPrintJob();
                try {
                    Doc doc = new SimpleDoc(bytes, flavor, null);
                    job.print(doc, null);
                } catch (PrintException e) {
                    Mensajes.error("Error printBytes: "+e);
                }
            }
        }
        public void printString2(String printerName, String text) {
		
		// find the printService of name printerName
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
		PrintService service = findPrintService(printerName, printService);
 
		DocPrintJob job = service.createPrintJob();
 
		try {
 
			byte[] bytes;
 
			// important for umlaut chars
			bytes = text.getBytes("CP437");
 
			Doc doc = new SimpleDoc(bytes, flavor, null);
 
			
			job.print(doc, null);
 
		} catch (UnsupportedEncodingException | PrintException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
                        Mensajes.error("La impresora no acepta el trabajo.\nEs posible que la impresora no este disponible.");
		}
 
	}
        
        public void printString3(String printerName, FileInputStream inputStream) {
		
		// find the printService of name printerName
		DocFlavor flavor = DocFlavor.INPUT_STREAM.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
		PrintService service = findPrintService(printerName, printService);
 
		DocPrintJob job = service.createPrintJob();
 
		try {
 			/*byte[] bytes;
			// important for umlaut chars
			bytes = inputStream.getBytes("CP437");*/
			Doc doc = new SimpleDoc(inputStream, flavor, null);

			job.print(doc, null);
 
		} catch (PrintException e) {
			// TODO Auto-generated catch block
			//e.printStackTrace();
                        Mensajes.error("La impresora no acepta el trabajo.\nEs posible que la impresora no este disponible.");
		}
 
	}
 
	public void printBytes2(String printerName, byte[] bytes) {
		
		DocFlavor flavor = DocFlavor.BYTE_ARRAY.AUTOSENSE;
		PrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();
 
		PrintService printService[] = PrintServiceLookup.lookupPrintServices(
				flavor, pras);
		PrintService service = findPrintService(printerName, printService);
 
		DocPrintJob job = service.createPrintJob();
 
		try {
 
			Doc doc = new SimpleDoc(bytes, flavor, null);
 
			job.print(doc, null);
 
		} catch (PrintException e) {
			//e.printStackTrace();
		}
	}
	
	/*private PrintService findPrintService(String printerName,
			PrintService[] services) {
		for (PrintService service : services) {
			if (service.getName().equalsIgnoreCase(printerName)) {
				return service;
			}
		}
 
		return null;
	}*/
}
