/*
This program is free software: you can redistribute it and/or modify
    it under the terms of the GNU General Public License version 3 as published by
    the Free Software Foundation.

    This program is distributed in the hope that it will be useful,
    but WITHOUT ANY WARRANTY; without even the implied warranty of
    MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
    GNU General Public License for more details.

    You should have received a copy of the GNU General Public License
    along with this program.  If not, see <http://www.gnu.org/licenses/>.
*/


package org.cirqwizard.gerber;

import org.cirqwizard.geom.Point;

import java.util.ArrayList;
import java.util.List;

public class Region extends GerberPrimitive
{
    private List<GerberPrimitive> segments = new ArrayList<>();

    public Region(Polarity polarity)
    {
        super(polarity);
    }

    public void addSegment(GerberPrimitive segment)
    {
        segments.add(segment);
    }

    public List<GerberPrimitive> getSegments()
    {
        return segments;
    }

    @Override
    public void rotate(boolean clockwise)
    {
        for (GerberPrimitive segment : segments)
            segment.rotate(clockwise);
    }

    @Override
    public void move(Point point)
    {
        for (GerberPrimitive segment : segments)
            segment.move(point);
    }

    @Override
    public Point getMin()
    {
        Point min = new Point(Integer.MAX_VALUE, Integer.MAX_VALUE);
        for (GerberPrimitive p : segments)
        {
            if (p.getMin().getX() < min.getX())
                min = new Point(p.getMin().getX(), min.getY());
            if (p.getMin().getY() < min.getY())
                min = new Point(min.getX(), p.getMin().getY());
        }
        return min;
    }
}
