package Project.Project;

import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;

public class ExtendedDocument {

	String documentName;
	int occurrence;
	int relevance = 1;
	int popularity;
	
	public Document document;
	public Attributes attr;
	
	public ExtendedDocument(Attributes attribute)
	{
		this.document = attribute.document;
		this.documentName = this.document.name;
		this.occurrence = attribute.occurrence;
		this.attr = attribute;
	}
}
