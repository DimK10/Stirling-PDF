import PDFLib from "pdf-lib";
import {rgb, degrees, StandardFonts} from 'pdf-lib';


/**
 * @typedef {Object} Metadata
 * @property {string | null | undefined} Title - The title of the document.
 * @property {string | null | undefined} Author - The author of the document.
 * @property {string | null | undefined} Subject - The subject of the document.
 * @property {string[] | null | undefined} Keywords - An array of keywords associated with the document.
 * @property {string | null | undefined} Producer - The producer of the document.
 * @property {string | null | undefined} Creator - The creator of the document.
 * @property {Date | null | undefined} CreationDate - The date when the document was created.
 * @property {Date | null | undefined} ModificationDate - The date when the document was last modified.
 */

/**
 *
 * @param {Uint16Array} snapshot
 * @param {import('pdf-lib')} PDFLib
 * @returns
 */
export async function addWatermark(snapshot, PDFLib) {
    const pdfDoc = await PDFLib.PDFDocument.load(snapshot);

    const helveticaFont = await pdfDoc.embedFont(StandardFonts.Helvetica)

    pdfDoc.getPages().forEach(page => {
        const { width, height } = page.getSize()
        page.drawText('This text was added with JavaScript!', {
            x: 5,
            y: height / 2 + 300,
            size: 50,
            font: helveticaFont,
            color: rgb(0.95, 0.1, 0.1),
            rotate: degrees(-45),
        })
    })

    return pdfDoc.save();
}