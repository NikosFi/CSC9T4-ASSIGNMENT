<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="2.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:key name="ref-by-journal" match="ref[@type='RefJournal']" use="journal"/>
	<xsl:key name="ref-by-journalPublisher" match="ref[@type='RefJournal']" use="publisher"/>
	<xsl:key name="ref-by-venue" match="ref[@type='RefConfernce']" use="venue"/>
	<xsl:key name="ref-by-location" match="ref[@type='RefConfernce']" use="location"/>
	<xsl:key name="ref-by-book" match="ref[@type='RefBookChapter']" use="book"/>
	<xsl:key name="ref-by-bookPublisher" match="ref[@type='RefBookChapter']" use="publisher"/>
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
								select="ref[@type='RefJournal'][count(generate-id() = generate-id(key('ref-by-journal', journal)[1]))]">
							<xsl:sort select="ref[count(key('ref-by-journal', journal))]" data-type="number"
									  order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="journal"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent Journal publisher</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefJournal'][count(. | key('ref-by-journalPublisher', publisher)[1]) = 1]">
							<xsl:sort select="ref[count(key('ref-by-journalPublisher', publisher))]"
									  data-type="number"
									  order="descending"/>
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
								select="ref[@type='RefConference'][count(. | key('ref-by-venue', venue)[1]) = 1]">
							<xsl:sort select="ref[count(key('ref-by-venue', venue))]" data-type="number"
									  order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="venue"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent location</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefConference'][count(. | key('ref-by-location', location)[1]) = 1]">
							<xsl:sort select="ref[count(key('ref-by-location', location))]" data-type="number"
									  order="descending"/>
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
						<xsl:value-of select="count(ref/venue)"/>
					</dd>
					<li>Most frequent book</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefConference'][count(. | key('ref-by-venue', venue)[1]) = 1]">
							<xsl:sort select="ref[count(key('ref-by-venue', venue))]" data-type="number"
									  order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="venue"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
					<li>Most frequent publisher</li>
					<dd>
						<xsl:for-each
								select="ref[@type='RefConference'][count(. | key('ref-by-location', location)[1]) = 1]">
							<xsl:sort select="ref[count(key('ref-by-location', location))]" data-type="number"
									  order="descending"/>
							<xsl:if test="position() = 1">
								<xsl:value-of select="location"/>
							</xsl:if>
						</xsl:for-each>
					</dd>
				</ul>
			</body>
		</html>
	</xsl:template>
</xsl:stylesheet>
