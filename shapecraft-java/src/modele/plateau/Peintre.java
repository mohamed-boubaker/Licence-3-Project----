package modele.plateau;

import modele.item.Item;
import modele.item.ItemColor;
import modele.item.ItemShape;

public class Peintre extends Machine {
    private ItemShape pendingShape;
    private ItemColor pendingColor;
    private ItemShape paintedOutput;

    @Override
    public Point[] getFootprint(Direction direction) {
        Direction secondaryOffsetDirection = direction.nextClockwise();
        return new Point[] {
                new Point(0, 0),
                new Point(secondaryOffsetDirection.dx, secondaryOffsetDirection.dy)
        };
    }

    @Override
    public void work() {
        if (paintedOutput != null || pendingShape == null || pendingColor == null) {
            return;
        }

        pendingShape.Color(pendingColor.getColor());
        paintedOutput = pendingShape;
        pendingShape = null;
        pendingColor = null;
    }

    @Override
    public void send() {
        Case secondaryCase = getSecondaryCase();
        if (secondaryCase == null || paintedOutput == null) {
            return;
        }

        Case outputCase = c.plateau.getCase(secondaryCase, getOutputDirection());
        if (outputCase == null || outputCase.getMachine() == null) {
            return;
        }

        if (outputCase.getMachine().receive(paintedOutput, outputCase, secondaryCase)) {
            paintedOutput = null;
        }
    }

    @Override
    public boolean receive(Item item, Case targetCase, Case sourceCase) {
        if (c == null || targetCase == null || sourceCase == null || paintedOutput != null) {
            return false;
        }

        Case secondaryCase = getSecondaryCase();

        if (targetCase == c
                && item instanceof ItemShape shape
                && pendingShape == null
                && c.plateau.getCase(sourceCase, getShapeInputDirection()) == c) {
            pendingShape = shape;
            return true;
        }

        if (secondaryCase != null
                && targetCase == secondaryCase
                && item instanceof ItemColor color
                && pendingColor == null
                && c.plateau.getCase(sourceCase, getColorInputDirection()) == secondaryCase) {
            pendingColor = color;
            return true;
        }

        return false;
    }

    @Override
    public Item getDisplayedItem(Case currentCase) {
        if (currentCase == null) {
            return null;
        }
        if (currentCase == c) {
            return pendingShape;
        }
        if (currentCase == getSecondaryCase()) {
            if (paintedOutput != null) {
                return paintedOutput;
            }
            return pendingColor;
        }
        return null;
    }

    @Override
    public void clearItems() {
        super.clearItems();
        pendingShape = null;
        pendingColor = null;
        paintedOutput = null;
    }

    private Case getSecondaryCase() {
        if (c == null) {
            return null;
        }
        return c.plateau.getCase(c, getSecondaryOffsetDirection());
    }

    private Direction getSecondaryOffsetDirection() {
        return d.nextClockwise();
    }

    private Direction getShapeInputDirection() {
        return rotateRelative(Direction.East);
    }

    private Direction getColorInputDirection() {
        return rotateRelative(Direction.South);
    }

    private Direction getOutputDirection() {
        return rotateRelative(Direction.East);
    }

    private Direction rotateRelative(Direction absoluteDirectionWhenFacingNorth) {
        Direction rotatedDirection = absoluteDirectionWhenFacingNorth;
        Direction currentOrientation = Direction.North;
        while (currentOrientation != d) {
            rotatedDirection = rotatedDirection.nextClockwise();
            currentOrientation = currentOrientation.nextClockwise();
        }
        return rotatedDirection;
    }
}
