<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:key name="ref-journal" match="ref[@type='RefJournal']" use="journal"/>
	<xsl:key name="ref-journalPub" match="ref[@type='RefJournal']" use="publisher"/>
	<xsl:key name="ref-venue" match="ref[@type='RefConference']" use="venue"/>
	<xsl:key name="ref-location" match="ref[@type='RefConference']" use="location"/>
	<xsl:key name="ref-book" match="ref[@type='RefBookChapter']" use="book"/>
	<xsl:key name="ref-bookPub" match="ref[@type='RefBookChapter']" use="publisher"/>
	<xsl:template match="/bibliography">
		<html>
			<body style="font-family:Arial;font-size:12pt;background-color:#EEEEEE">
				<h1>Bibliography summary</h1>
				<h2>Journal articles</h2>
				<ul>
					<li>Number of journal articles</li>
					<dd>
						<xsl:value-of select="count(ref/journal) "/>
					</dd>
					<li>Most frequent journal</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefJournal'][count(. | key('ref-journal', journal)[1]) = 1]">
							<xsl:sort select="count(key('ref-journal', journal))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="journal"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent Journal publisher</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefJournal'][count(. | key('ref-journalPub', publisher)[1]) = 1]">
							<xsl:sort select="count(key('ref-journalPub', publisher))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="publisher"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
				</ul>
				<h2>Conference proceedings</h2>
				<ul>
					<li>Number of conference proceedings</li>
					<dd>
						<xsl:value-of select="count(ref/venue)"/>
					</dd>
					<li>Most frequent venue</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefConference'][count(. | key('ref-venue', venue)[1]) = 1]">
							<xsl:sort select="count(key('ref-venue', venue))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="venue"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent location</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefConference'][count(. | key('ref-location', location)[1]) = 1]">
							<xsl:sort select="count(key('ref-location', location))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="location"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
				</ul>
				<h2>Book chapters</h2>
				<ul>
					<li>Number of book chapters</li>
					<dd>
						<xsl:value-of select="count(ref/book)"/>
					</dd>
					<li>Most frequent book</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefBookChapter'][count(. | key('ref-book', book)[1]) = 1]">
							<xsl:sort select="count(key('ref-book', book))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="book"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent publisher</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefBookChapter'][count(. | key('ref-bookPub', publisher)[1]) = 1]">
							<xsl:sort select="count(key('ref-bookPub', publisher))" data-type="number" order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="publisher"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
				</ul>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>