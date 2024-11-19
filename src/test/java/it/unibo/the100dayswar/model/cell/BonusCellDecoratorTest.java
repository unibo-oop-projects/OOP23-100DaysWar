package it.unibo.the100dayswar.model.cell;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;



import it.unibo.the100dayswar.model.cell.impl.BonusCellDecorator;
import it.unibo.the100dayswar.model.cell.impl.BuildableCellImpl;
import it.unibo.the100dayswar.commons.utilities.impl.PositionImpl;
import org.junit.jupiter.api.Test;


class BonusCellDecoratorTest {

    @Test
    void testBonusActivation() {
    final BuildableCellImpl baseCell = new BuildableCellImpl(new PositionImpl(3, 3), true, true);
    final BonusCellDecorator bonusCell = new BonusCellDecorator(baseCell);

    assertTrue(bonusCell.isBonusActive(), "Bonus should be active initially");
    bonusCell.setBonusActive(false);
    assertFalse(bonusCell.isBonusActive(), "Bonus should be inactive after deactivation");
    }

    @Test
    void testBonusAmount() {
    final BuildableCellImpl baseCell = new BuildableCellImpl(new PositionImpl(5, 5), true, false);
    final BonusCellDecorator bonusCell = new BonusCellDecorator(baseCell);

    assertEquals(100, bonusCell.getAmount(), "Bonus amount should be 100");
    }
}

