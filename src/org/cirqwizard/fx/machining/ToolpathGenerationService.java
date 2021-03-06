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

package org.cirqwizard.fx.machining;

import javafx.beans.property.*;
import javafx.collections.ObservableList;
import javafx.concurrent.Service;
import javafx.concurrent.Task;
import org.cirqwizard.fx.Context;
import org.cirqwizard.fx.MainApplication;
import org.cirqwizard.toolpath.Toolpath;


public abstract class ToolpathGenerationService extends Service<ObservableList<Toolpath>>
{
    protected MainApplication mainApplication;
    protected StringProperty generationStageProperty = new SimpleStringProperty();
    protected DoubleProperty overallProgressProperty;
    protected StringProperty estimatedMachiningTimeProperty;
    protected Context context;

    public ToolpathGenerationService(MainApplication mainApplication, DoubleProperty overallProgressProperty,
                                     StringProperty estimatedMachiningTimeProperty)
    {
        this.mainApplication = mainApplication;
        this.context = mainApplication.getContext();
        this.overallProgressProperty = overallProgressProperty;
        this.estimatedMachiningTimeProperty = estimatedMachiningTimeProperty;
    }

    @Override
    protected abstract Task<ObservableList<Toolpath>> createTask();

    public boolean needsRestart()
    {
        return true;
    }

    public StringProperty generationStageProperty()
    {
        return generationStageProperty;
    }


}
