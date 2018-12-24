<?xml version="1.0" encoding="UTF-8"?>
<xsl:stylesheet version="1.0"
	xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">
		<html>
			<body>
				<h2>Informacja o średnim kursie waluty</h2>
				<p>
					<b>Nazwa: </b>
					<xsl:value-of select="Waluta/Nazwa_waluty" />
				</p>
				<p>
					<b>Kod: </b>
					<xsl:value-of select="Waluta/Kod_waluty" />
				</p>
				<xsl:if test = "Waluta/Średni_kurs_ceny_kupna>0">
				<p>
				
					<b>Średni kurs ceny kupna: </b>
					<xsl:value-of select="Waluta/Średni_kurs_ceny_kupna" />
				</p>
				</xsl:if>
				<xsl:if test = "Waluta/Średni_kurs_ceny_sprzedaży>0">
				<p>
					<b>Średni kurs ceny sprzedaży: </b>
					<xsl:value-of select="Waluta/Średni_kurs_ceny_sprzedaży" />
				</p>
				</xsl:if>
				<xsl:if test = "Waluta/Średni_kurs>0">
				<p>
					<b>Średni kurs: </b>
					<xsl:value-of select="Waluta/Średni_kurs" />
				</p>
				</xsl:if>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>