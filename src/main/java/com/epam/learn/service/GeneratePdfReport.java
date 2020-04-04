package com.epam.learn.service;

import java.io.ByteArrayInputStream;
import com.epam.learn.model.UserAccount;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.Phrase;
import com.itextpdf.text.pdf.PdfPCell;
import com.itextpdf.text.pdf.PdfPTable;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.ByteArrayOutputStream;
import java.util.List;

public class GeneratePdfReport {
    public static ByteArrayInputStream createReport(List<UserAccount> userAccounts) {

                Document document = new Document();
                ByteArrayOutputStream out = new ByteArrayOutputStream();
                try {
                    PdfPTable table = new PdfPTable(5);
                    table.setWidthPercentage(60);
                    table.setWidths(new int[]{1, 3, 3, 3, 3});
                    Font headFont = FontFactory.getFont(FontFactory.HELVETICA_BOLD);
                    PdfPCell hcell;
                    hcell = new PdfPCell(new Phrase("Id", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);
                    hcell = new PdfPCell(new Phrase("Name", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);
                    hcell = new PdfPCell(new Phrase("Phone Number", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);

                    hcell = new PdfPCell(new Phrase("Phone Operator", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);

                    hcell = new PdfPCell(new Phrase("Balance", headFont));
                    hcell.setHorizontalAlignment(Element.ALIGN_CENTER);
                    table.addCell(hcell);

                    for (UserAccount userAccount : userAccounts) {
                        PdfPCell cell;

                        cell = new PdfPCell(new Phrase(userAccount.getId().toString()));
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_CENTER);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(userAccount.getName()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(userAccount.getPhoneNumber()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(userAccount.getPhoneOperator()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);

                        cell = new PdfPCell(new Phrase(userAccount.getBalance()));
                        cell.setPaddingLeft(5);
                        cell.setVerticalAlignment(Element.ALIGN_MIDDLE);
                        cell.setHorizontalAlignment(Element.ALIGN_LEFT);
                        table.addCell(cell);
                    }

                    PdfWriter.getInstance(document, out);
                    document.open();
                    document.add(table);
                    document.close();
                } catch (DocumentException ex) {
                    //TODO Process ex
                }
                return new ByteArrayInputStream(out.toByteArray());
            }
        }
