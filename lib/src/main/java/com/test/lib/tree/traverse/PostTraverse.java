package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.List;

/**
 * εεΊιε
 */
public class PostTraverse implements ITraverse {

    @Override
    public void order(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return;
        }
        order(treeNode.left, result);
        order(treeNode.right, result);
        result.add(treeNode.val);
    }
}
