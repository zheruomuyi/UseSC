<?xml version="1.0" encoding="ISO-8859-1"?>
<xsl:stylesheet version="1.0" xmlns:xsl="http://www.w3.org/1999/XSL/Transform">
	<xsl:template match="/">  
        <html>  
            <head>  
                <title>a viewer in MVC</title>  
            </head>  
            <body>  
                <form>
                <xsl:attribute name="name"><xsl:value-of select="view/body/form/name"/></xsl:attribute> 
                <xsl:attribute name="action"><xsl:value-of select="view/body/form/action"/></xsl:attribute> 
                <xsl:attribute name="method"><xsl:value-of select="view/body/form/method"/></xsl:attribute> 
	        		<xsl:for-each select="view/body/form/textview">	
	        			<xsl:value-of select="label"/>
	        			<br/>  
	        			<input>
	        				<xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
		        	 		<xsl:attribute name="type"><xsl:value-of select="type"/></xsl:attribute>   
	        			</input>					
			          	<br/>		          
	 				</xsl:for-each>  	 
	 				<br/>
	 			<xsl:for-each select="view/body/form/buttonView">	
        			<br/>  
			        <input> 
			        	 <xsl:attribute name="name"><xsl:value-of select="name"/></xsl:attribute>
			        	 <xsl:attribute name="value"><xsl:value-of select="value"/></xsl:attribute>      
			        	 <xsl:attribute name="type"><xsl:value-of select="type"/></xsl:attribute>      
			        </input>
		        	<br/>		          
	 			</xsl:for-each>
          	</form>
          </body>  
        </html>  
    </xsl:template>
</xsl:stylesheet>