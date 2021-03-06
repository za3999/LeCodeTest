package com.test.lib.tree.traverse;

import com.test.lib.tree.TreeNode;

import java.util.List;

/**
 * εεΊιε
 */
public class PreTraverse implements ITraverse {

    @Override
    public void order(TreeNode treeNode, List<Integer> result) {
        if (treeNode == null) {
            return;
        }
        result.add(treeNode.val);
        order(treeNode.left, result);
        order(treeNode.right, result);
    }
}
