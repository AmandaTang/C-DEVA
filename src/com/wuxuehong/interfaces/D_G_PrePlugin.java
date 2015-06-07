package com.wuxuehong.interfaces;

/**
 * 
 * @author Tangyu
 * @date: 2015年6月2日 下午5:34:11
 * add interface for disease gene prediction plug-in
 *
 */
public interface D_G_PrePlugin extends NewAlgorithm{
	 /**
     * 
     * @return the candidate disease genes
     */
    abstract public String[] getCandidateGenes();

}
