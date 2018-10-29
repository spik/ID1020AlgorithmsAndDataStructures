package Project.Project;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import Project.Project.QueryParser.SortType;
import se.kth.id1020.Driver;
import se.kth.id1020.TinySearchEngineBase;
import se.kth.id1020.util.Attributes;
import se.kth.id1020.util.Document;
import se.kth.id1020.util.Word;

public class TinySearchEngine implements TinySearchEngineBase {

	static List<Object> objectList = new ArrayList<Object>();

	public static void main(String[] args) throws Exception{

		TinySearchEngineBase searchEngine = new TinySearchEngine();
		Driver.run(searchEngine);

	}

	public void insert (Word word, Attributes attr)
	{
		//Create a new object every time this method is called from driver, and inputs the word and attribute. 
		Object newObject = new Object (word, attr);	

		//add the object in the objectlist. 
		objectList.add(newObject);
	}

	public List<Document> search(String query)
	{
		List<ExtendedDocument> extendedDocumentList = new ArrayList <ExtendedDocument>();

		QueryParser parser = new QueryParser(query);

		//Making a String-array that gets the values from the terms-array.
		//"searchTerms" will now contain all the words the user searched.
		String[] searchTerms = parser.terms;

		//Gets a document result-set for each supplied search term.
		//for each term in the array "searchTerms"...
		for(String term : searchTerms)
		{
			//...add the words in the documentList according to the method "getMatchingDocumentsByTerm". 
			extendedDocumentList.addAll(getMatchingDocumentsByTerm(term));
			
		}
		System.out.println("BEFORE GET RELEVANCE 2");
		printExDocs(extendedDocumentList);

		//Call relevance again, this time to get the relevance after the union of the search words. 
		List<ExtendedDocument> filteredExtendedDocumentList = getRelevanceAndRemoveDuplicates(extendedDocumentList);
		
		System.out.println("AFTER GET RELEVANCE 2");
		printExDocs(filteredExtendedDocumentList);

		//Call mergeSort to sort the filtered list. 
		filteredExtendedDocumentList = mergeSort(filteredExtendedDocumentList, parser.type);
		
		System.out.println("AFTER MERGESORT");
		printExDocs(filteredExtendedDocumentList);
		
		//If the direction is descending...
		if (!parser.isAsc)
		{
			//...reverse the list
			Collections.reverse(filteredExtendedDocumentList);
		}
		
		System.out.println("AFTER REVERSE");
		printExDocs(filteredExtendedDocumentList);
		
		List<Document> filteredDocList = new ArrayList<Document>();
		
		//Because this method must return a list of type "Document", get all the documents from the extended document list
		for(ExtendedDocument exDoc : filteredExtendedDocumentList)
		{
			filteredDocList.add(exDoc.document);
		}
		
		return filteredDocList;
	}

	//This method is my previous search method. Moved to a separate method to get cleaner code.
	public List<ExtendedDocument> getMatchingDocumentsByTerm (String term)
	{
		List<ExtendedDocument> matchingDocumentList = new ArrayList <ExtendedDocument>();
		
		//Loop through all indices in the array
		for (int i = 0; i < objectList.size(); i++)
		{
			//if the word in object "word" on the index "i" in list "objectList" matches the term...
			if (objectList.get(i).word.word.equals(term))
			{
				//...add it to the ExtendedDocument list. 
				ExtendedDocument newExDoc = new ExtendedDocument (objectList.get(i).attr);
				matchingDocumentList.add(newExDoc);
			}
		}
		//Call the getRelevance method to calculate the relevance. 
		List<ExtendedDocument> filteredDocs = getRelevanceAndRemoveDuplicates(matchingDocumentList);

		return filteredDocs;
	}
	
	public List<ExtendedDocument> getRelevanceAndRemoveDuplicates(List<ExtendedDocument> extendedDocumentList) 
	{
		List<ExtendedDocument> filteredDocs = new ArrayList<ExtendedDocument>();
		
		//Loop through each document in the extendedDocumentList
		for(ExtendedDocument doc1 : extendedDocumentList)
		{
			//Create a new ExtendedDocument and set it to null 
			ExtendedDocument existingDocument = null;
			
			//Loop through each document in the filteredDocs-list
			for(ExtendedDocument doc2 : filteredDocs)
			{
				//If the document name from the extendedDocumentList is the same as the name in the filteredDocs
				if(doc2.documentName.equals(doc1.documentName))
				{
					//We have found the already added document, which means that this is a duplicate. 
					//And we add this document to the existingDocument
					existingDocument = doc2;
				}
			}
			//Create a boolean isAlreadyAdded
			boolean isAlreadyAdded = existingDocument != null;
			
			//If existing document is not null, isAlreadyAdded is true (because we have already added this document)
			if(isAlreadyAdded)
			{
				//In this case, add the relevance for doc1 to the relevance of the existing document. 
				existingDocument.relevance += doc1.relevance;
			}
			else
			{
				//else, add the document in the filteredDocs-list (as it is not already in there)
				filteredDocs.add(doc1);
			}
		}
		
		return filteredDocs;
	}
	
	//This is just a method to print a list of ExtendedDocuments 
	public static void printExDocs(List<ExtendedDocument> list)
	{
		System.out.println("PRINTS LIST:");
		
		for(ExtendedDocument doc : list)
		{
			System.out.print(" NAME: " + doc.documentName);
			System.out.print(" OCCUR: " + doc.occurrence);
			System.out.print(" REL: " + doc.relevance);
			System.out.print(" POP: " + doc.document.popularity);
			
			System.out.println("");
		}
		
		System.out.println("");
	}
	
	public static List<ExtendedDocument> mergeSort (List <ExtendedDocument> documentList, SortType type)
	{

		List<ExtendedDocument> left = new ArrayList<ExtendedDocument>();
		List<ExtendedDocument> right = new ArrayList<ExtendedDocument>();
		int mid;

		if(documentList.size()==1 || documentList.size()==0)    
			return documentList;
		else
		{
			mid = documentList.size()/2;

			//copy the left half of documentList into the list "left".
			for(int i = 0; i < mid; i++)
			{
				left.add(documentList.get(i));
			}

			//copy the right half of documentList into the list "right".
			for(int j = mid; j < documentList.size(); j++)
			{
				right.add(documentList.get(j));
			}

			// recursive call to divide list 
			left  = mergeSort(left, type);
			right = mergeSort(right, type);

			// Merge the results back together.
			merge(left,right,documentList, type);

		}

		return documentList;
	}

	public static void merge (List <ExtendedDocument> left, List<ExtendedDocument> right, List<ExtendedDocument> whole, SortType type)
	{

		int leftIndex = 0;
		int rightIndex = 0;
		int wholeIndex = 0;

		while (leftIndex < left.size() && rightIndex < right.size())
		{
			int result = 1;

			//using switch-cases to differentiate the properties
			switch (type) 
			{
			case popularity: 
				//Compare the value at the left index of the left list  with the value at the right index of the right list
				//if the value in the left list is smaller than the value in the right list, result is set to -1.
				if (left.get(leftIndex).document.popularity < right.get(rightIndex).document.popularity)
					result = -1;
				break;
			
			case relevance: 
				if (left.get(leftIndex).relevance < right.get(rightIndex).relevance)
					result = -1;
				break;

			case occurrence:
				if (left.get(leftIndex).attr.occurrence < right.get(rightIndex).attr.occurrence)
					result = -1;
				break;
			}
			
			//If the result of the requested compare is less than 0
			if (result < 0)
			{
				//whole list gets value from the left list 
				whole.set(wholeIndex,left.get(leftIndex));
				leftIndex++;
			}
			else
			{
				//else, whole gets value from the right list
				whole.set(wholeIndex, right.get(rightIndex));
				rightIndex++;
			}
			//update indices for the next loop. 
			wholeIndex++;
		}
		
		//The following code is for when one of the lists has been exhausted, and only elements from the other list remains. 
		//create a new list called "rest"
		List <ExtendedDocument> rest;
		int restIndex;
		
		//If the index of the left list is greater than the number of elements in the left list...
		if (leftIndex >= left.size()) 
		{
			//...the left arraylist has been used up
			rest = right;
			restIndex = rightIndex;
		}
		else 
		{
			//else, the right arraylist has been used up
			rest = left;
			restIndex = leftIndex;
		}

		//Add the values from the rest arraylist to the whole arraylist
		for (int i = restIndex; i < rest.size(); i++) 
		{
			whole.set(wholeIndex, rest.get(i));
			wholeIndex++;
		}
	}
}
