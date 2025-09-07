-- ========================================
-- Author:      jv-iatecola
-- Created:     2025-09-06
-- ========================================
-- Description:
-- - This file assumes an active connection to the target database.
-- - Modify with caution; production impact possible.
-- ========================================

CREATE OR REPLACE PROCEDURE add_character (p_name VARCHAR(30), p_class VARCHAR(20))
LANGUAGE plpgsql
AS $$
BEGIN
INSERT INTO characters (name, class)
VALUES (p_name, p_class);
END;
$$;