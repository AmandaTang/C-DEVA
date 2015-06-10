package com.wuxuehong.interfaces;

/**
 * 
 * @author Tangyu
 * @date: 2015��6��2�� ����5:34:11
 * add interface for disease gene prediction plug-in
 *
 */
public interface PredictionPlugin extends NewAlgorithm{
	 /**
     * 
     * @return the candidate disease genes
     */
    abstract public String[] getCandidateGenes();

}
