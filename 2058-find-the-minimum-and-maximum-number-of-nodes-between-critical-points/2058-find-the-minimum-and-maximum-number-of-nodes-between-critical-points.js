var nodesBetweenCriticalPoints = function(head) {
    let prev = head;
    let curr = head.next;

    let pos = 1;
    let first = -1;
    let prevCritical = -1;

    let minDist = Infinity;
    let lastCritical = -1;

    while (curr.next !== null) {
        let next = curr.next;

        // Check if curr is a local maximum or minimum
        let isCritical =
            (curr.val > prev.val && curr.val > next.val) ||
            (curr.val < prev.val && curr.val < next.val);

        if (isCritical) {
            if (first === -1) {
                // First critical point
                first = pos;
            } else {
                // Distance from previous critical point
                minDist = Math.min(minDist, pos - prevCritical);
            }

            prevCritical = pos;
            lastCritical = pos;
        }

        prev = curr;
        curr = next;
        pos++;
    }

    // Fewer than 2 critical points
    if (first === -1 || first === lastCritical) {
        return [-1, -1];
    }

    let maxDist = lastCritical - first;

    return [minDist, maxDist];
};
